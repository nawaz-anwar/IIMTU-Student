package com.coetusstudio.iimtustudent.Activity.Marks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Sessional_Assignment_Marks extends AppCompatActivity {

    RecyclerView recviewSessional;
    SessionalMarksAdapter sessionalMarksAdapter;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    String studentRollNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessional_assignment_marks);

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Student").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    studentRollNumber = snapshot.child("studentRollNumber").getValue().toString();
                    Log.i(studentRollNumber,"Roll");
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Sessional_Assignment_Marks.this);
                    recviewSessional=(RecyclerView)findViewById(R.id.rcSessionalMarks);
                    recviewSessional.setLayoutManager(linearLayoutManager);
                    linearLayoutManager.setReverseLayout(true);
                    linearLayoutManager.setStackFromEnd(true);

                    FirebaseRecyclerOptions<SessionalMarks> options =
                            new FirebaseRecyclerOptions.Builder<SessionalMarks>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("SessionalMarks").child(studentRollNumber), SessionalMarks.class)
                                    .build();

                    sessionalMarksAdapter=new SessionalMarksAdapter(options);
                    recviewSessional.setAdapter(sessionalMarksAdapter);
                    sessionalMarksAdapter.startListening();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        sessionalMarksAdapter.stopListening();
    }

}