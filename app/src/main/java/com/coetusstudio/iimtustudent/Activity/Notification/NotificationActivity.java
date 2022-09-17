package com.coetusstudio.iimtustudent.Activity.Notification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.coetusstudio.iimtustudent.Activity.Attendance.AttendanceActivity;
import com.coetusstudio.iimtustudent.Activity.Attendance.SelectSubjectAttendance;
import com.coetusstudio.iimtustudent.Activity.Home.MainActivity;
import com.coetusstudio.iimtustudent.Adapter.NoticeAdapter;
import com.coetusstudio.iimtustudent.Model.AddFaculty;
import com.coetusstudio.iimtustudent.Model.NoticeData;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {


    RecyclerView recviewNotice;
    NoticeAdapter noticeAdapter;
    String studentSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        FirebaseDatabase.getInstance().getReference().child("Student Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dsp : snapshot.getChildren()) {
                    try {
                        studentSection = dsp.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("studentSection").getValue(String.class).toString();

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotificationActivity.this);
                recviewNotice=(RecyclerView)findViewById(R.id.rcNotice);
                recviewNotice.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);

                FirebaseRecyclerOptions<NoticeData> options =
                        new FirebaseRecyclerOptions.Builder<NoticeData>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Notice").child(studentSection).child("All Faculty And Students"), NoticeData.class)
                                .build();

                noticeAdapter=new NoticeAdapter(options);
                recviewNotice.setAdapter(noticeAdapter);
                noticeAdapter.startListening();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NotificationActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        noticeAdapter.stopListening();
    }


}