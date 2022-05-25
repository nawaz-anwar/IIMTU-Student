package com.coetusstudio.iimtustudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coetusstudio.iimtustudent.Adapter.LectureAdapter;
import com.coetusstudio.iimtustudent.Model.Lecture;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class LectureActivity extends AppCompatActivity {

    RecyclerView recviewLecture;
    LectureAdapter lectureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        recviewLecture=(RecyclerView)findViewById(R.id.rcLecture);
        recviewLecture.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Lecture> options =
                new FirebaseRecyclerOptions.Builder<Lecture>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Lecture"), Lecture.class)
                        .build();

        lectureAdapter=new LectureAdapter(options);
        recviewLecture.setAdapter(lectureAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lectureAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        lectureAdapter.stopListening();
    }
}