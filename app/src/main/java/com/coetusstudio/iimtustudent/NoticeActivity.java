package com.coetusstudio.iimtustudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coetusstudio.iimtustudent.Adapter.LectureAdapter;
import com.coetusstudio.iimtustudent.Adapter.NoticeAdapter;
import com.coetusstudio.iimtustudent.Model.Lecture;
import com.coetusstudio.iimtustudent.Model.Notice;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class NoticeActivity extends AppCompatActivity {

    RecyclerView recviewNotice;
    NoticeAdapter noticeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        recviewNotice=(RecyclerView)findViewById(R.id.rcNotice);
        recviewNotice.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Notice> options =
                new FirebaseRecyclerOptions.Builder<Notice>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notice"), Notice.class)
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
}