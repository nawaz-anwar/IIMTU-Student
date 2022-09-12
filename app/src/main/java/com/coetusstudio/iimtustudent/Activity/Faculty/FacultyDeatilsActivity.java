package com.coetusstudio.iimtustudent.Activity.Faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import com.coetusstudio.iimtustudent.Activity.Attendance.AttendanceActivity;
import com.coetusstudio.iimtustudent.Activity.Attendance.SelectSubjectAttendance;
import com.coetusstudio.iimtustudent.Adapter.FacultyAdapter;
import com.coetusstudio.iimtustudent.Model.AddFaculty;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FacultyDeatilsActivity extends AppCompatActivity {

    RecyclerView recviewFaculty;
    FacultyAdapter facultyAdapter;
    String studentSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_deatils);
        setTitle("Enter Faculty ID");

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

                recviewFaculty=(RecyclerView)findViewById(R.id.rcFactulty);
                recviewFaculty.setLayoutManager(new LinearLayoutManager(FacultyDeatilsActivity.this));

                FirebaseRecyclerOptions<AddFaculty> options =
                        new FirebaseRecyclerOptions.Builder<AddFaculty>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Faculty Data").child(studentSection), AddFaculty.class)
                                .build();

                facultyAdapter=new FacultyAdapter(options);
                recviewFaculty.setAdapter(facultyAdapter);
                facultyAdapter.startListening();

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FacultyDeatilsActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    protected void onStop() {
        super.onStop();
        facultyAdapter.stopListening();

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
        FirebaseRecyclerOptions<AddFaculty> options =
                new FirebaseRecyclerOptions.Builder<AddFaculty>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Faculty").orderByChild("facultyId").startAt(s).endAt(s+"\uf8ff"), AddFaculty.class)
                        .build();

        facultyAdapter=new FacultyAdapter(options);
        facultyAdapter.startListening();
        recviewFaculty.setAdapter(facultyAdapter);


    }
}