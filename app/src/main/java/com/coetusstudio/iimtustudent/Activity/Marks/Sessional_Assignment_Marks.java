package com.coetusstudio.iimtustudent.Activity.Marks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.coetusstudio.iimtustudent.Adapter.SessionalMarksAdapter;
import com.coetusstudio.iimtustudent.Model.SessionalMarks;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sessional_Assignment_Marks extends AppCompatActivity {

    RecyclerView recviewSessional;
    SessionalMarksAdapter sessionalMarksAdapter;
    String studentRollNumber, studentSection, studentName;
    ArrayList<SessionalMarks> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessional_assignment_marks);

        Intent intent = getIntent();
        studentSection = intent.getStringExtra("section");
        studentRollNumber = intent.getStringExtra("rollNumber");
        studentName = intent.getStringExtra("name");
        
        list = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Sessional_Assignment_Marks.this);
        recviewSessional = (RecyclerView) findViewById(R.id.rcSessionalMarks);
        recviewSessional.setLayoutManager(linearLayoutManager);

        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);

        sessionalMarksAdapter = new SessionalMarksAdapter(this, list, studentName);
        recviewSessional.setAdapter(sessionalMarksAdapter);

        FirebaseDatabase.getInstance().getReference().child("SessionalMarks").child(studentSection).child(studentRollNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SessionalMarks sessionalMarks = dataSnapshot.getValue(SessionalMarks.class);
                    list.add(sessionalMarks);
                }
                sessionalMarksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}