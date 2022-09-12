package com.coetusstudio.iimtustudent.Activity.Lecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.coetusstudio.iimtustudent.Activity.Faculty.FacultyDeatilsActivity;
import com.coetusstudio.iimtustudent.Adapter.FacultyAdapter;
import com.coetusstudio.iimtustudent.Adapter.LectureAdapter;
import com.coetusstudio.iimtustudent.Activity.Home.MainActivity;
import com.coetusstudio.iimtustudent.Model.AddFaculty;
import com.coetusstudio.iimtustudent.Model.Lecture;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LectureActivity extends AppCompatActivity {

    RecyclerView recviewLecture;
    LectureAdapter lectureAdapter;
    String studentSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

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
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LectureActivity.this);
                recviewLecture=(RecyclerView)findViewById(R.id.rcLecture);
                recviewLecture.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);

                FirebaseRecyclerOptions<Lecture> options =
                        new FirebaseRecyclerOptions.Builder<Lecture>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Lecture").child(studentSection), Lecture.class)
                                .build();

                lectureAdapter=new LectureAdapter(options);
                recviewLecture.setAdapter(lectureAdapter);
                lectureAdapter.startListening();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LectureActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        lectureAdapter.stopListening();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<Lecture> options =
                new FirebaseRecyclerOptions.Builder<Lecture>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Lecture").child(studentSection).orderByChild("lectureName").startAt(s).endAt(s+"\uf8ff"), Lecture.class)
                        .build();

        lectureAdapter=new LectureAdapter(options);
        lectureAdapter.startListening();
        recviewLecture.setAdapter(lectureAdapter);

    }
}