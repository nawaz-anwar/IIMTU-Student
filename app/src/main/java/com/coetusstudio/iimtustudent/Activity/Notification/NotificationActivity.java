package com.coetusstudio.iimtustudent.Activity.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.coetusstudio.iimtustudent.Activity.Home.MainActivity;
import com.coetusstudio.iimtustudent.Adapter.NoticeAdapter;
import com.coetusstudio.iimtustudent.Model.NoticeData;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class NotificationActivity extends AppCompatActivity {


    RecyclerView recviewNotice;
    NoticeAdapter noticeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotificationActivity.this);
        recviewNotice=(RecyclerView)findViewById(R.id.rcNotice);
        recviewNotice.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        FirebaseRecyclerOptions<NoticeData> options =
                new FirebaseRecyclerOptions.Builder<NoticeData>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notice").child("All Faculty And Students"), NoticeData.class)
                        .build();

        noticeAdapter=new NoticeAdapter(options);
        recviewNotice.setAdapter(noticeAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        noticeAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        noticeAdapter.stopListening();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}