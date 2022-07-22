package com.coetusstudio.iimtustudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coetusstudio.iimtustudent.Adapter.LectureAdapter;
import com.coetusstudio.iimtustudent.Adapter.SessionalMarksAdapter;
import com.coetusstudio.iimtustudent.Model.Lecture;
import com.coetusstudio.iimtustudent.Model.SessionalMarks;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Sessional_Assignment_Marks extends AppCompatActivity {

    RecyclerView recviewSessional;
    SessionalMarksAdapter sessionalMarksAdapter;
    FirebaseAuth auth;
    FirebaseUser currentUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessional_assignment_marks);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Sessional_Assignment_Marks.this);
        recviewSessional=(RecyclerView)findViewById(R.id.rcSessionalMarks);
        recviewSessional.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        FirebaseRecyclerOptions<SessionalMarks> options =
                new FirebaseRecyclerOptions.Builder<SessionalMarks>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SessionalMarks"), SessionalMarks.class)
                        .build();

        sessionalMarksAdapter=new SessionalMarksAdapter(options);
        recviewSessional.setAdapter(sessionalMarksAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sessionalMarksAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sessionalMarksAdapter.stopListening();
    }

}