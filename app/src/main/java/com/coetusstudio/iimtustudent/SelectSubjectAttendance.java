package com.coetusstudio.iimtustudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.coetusstudio.iimtustudent.Model.AddFaculty;
import com.coetusstudio.iimtustudent.databinding.ActivitySelectSubjectAttendanceBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectSubjectAttendance extends AppCompatActivity {

    ActivitySelectSubjectAttendanceBinding binding;
    String item_subject, studentSection;
    DatabaseReference dbSubjectRef, dbSectionRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectSubjectAttendanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbSubjectRef = FirebaseDatabase.getInstance().getReference().child("AttendenRecordSheet");
        dbSectionRef = FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Student");

        dbSectionRef.child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentSection=snapshot.child("studentSection").getValue().toString();


                //Spinner for Subject
                final List<String> listSubject=new ArrayList<String>();
                listSubject.add("Select Subject");

                ArrayAdapter<String> subjectArrayAdapter=new ArrayAdapter<String>(SelectSubjectAttendance.this,android.R.layout.simple_spinner_item,listSubject);
                subjectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.selectSubTv.setAdapter(subjectArrayAdapter);

                binding.selectSubTv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        item_subject=parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                dbSubjectRef.child(studentSection).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot dsp :dataSnapshot.getChildren()){

                            listSubject.add(dsp.getValue().toString());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}