package com.coetusstudio.iimtustudent.Activity.Attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Activity.Home.LoginActivity;
import com.coetusstudio.iimtustudent.Activity.Home.MainActivity;
import com.coetusstudio.iimtustudent.Model.AddFaculty;
import com.coetusstudio.iimtustudent.R;
import com.coetusstudio.iimtustudent.databinding.ActivitySelectSubjectAttendanceBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectSubjectAttendance extends AppCompatActivity {

    ActivitySelectSubjectAttendanceBinding binding;
    String item_subject, studentSection, studentRollNumber, studentName;
    DatabaseReference dbSubjectRef;
    FirebaseAuth auth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectSubjectAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        //dbSectionRef = FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Student");
        dbSubjectRef = FirebaseDatabase.getInstance().getReference().child("Faculty Data");
        dbSubjectRef.keepSynced(true);

        FirebaseDatabase.getInstance().getReference().child("Student Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dsp : snapshot.getChildren()) {
                    try {
                        studentName= dsp.child(auth.getCurrentUser().getUid()).child("studentName").getValue(String.class).toString();
                        studentSection = dsp.child(auth.getCurrentUser().getUid()).child("studentSection").getValue(String.class).toString();
                        studentRollNumber = dsp.child(auth.getCurrentUser().getUid()).child("studentRollNumber").getValue(String.class).toString();

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                //Spinner for Subject
                final List<String> listSubject = new ArrayList<String>();
                listSubject.add("Select Subject");

                ArrayAdapter<String> subjectArrayAdapter = new ArrayAdapter<String>(SelectSubjectAttendance.this, android.R.layout.simple_spinner_item, listSubject);
                subjectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.selectSubTv.setAdapter(subjectArrayAdapter);

                binding.selectSubTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        item_subject = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                dbSubjectRef.child(studentSection).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                            AddFaculty br = dsp.getValue(AddFaculty.class);

                            listSubject.add(br.getFacultySubject());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



                binding.proceedBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SelectSubjectAttendance.this, AttendanceActivity.class);
                        intent.putExtra("subjectName",item_subject);
                        intent.putExtra("studentSection",studentSection);
                        intent.putExtra("studentRollNumber",studentRollNumber);
                        intent.putExtra("studentName",studentName);
                        startActivity(intent);
                    }
                });

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SelectSubjectAttendance.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });



    }
}