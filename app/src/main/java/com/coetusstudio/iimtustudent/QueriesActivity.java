package com.coetusstudio.iimtustudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.coetusstudio.iimtustudent.Model.Queries;
import com.coetusstudio.iimtustudent.Model.StudentDetails;
import com.coetusstudio.iimtustudent.databinding.ActivityQueriesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueriesActivity extends AppCompatActivity {

    ActivityQueriesBinding binding;
    DatabaseReference reference;
    DatabaseReference dbnameref, dbrollref;
    String studentName, studentRollNumber;
    HashMap<String,String> hashMapstudentName=new HashMap<>();
    HashMap<String,String> hashMapStudentRollNumber=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQueriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference().child("Queries");
        dbrollref = FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Student");
        dbnameref = FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Student");

        binding.btnSendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.queriesTitle.getEditText().getText().toString().isEmpty()) {
                    binding.queriesTitle.setError("Empty");
                    binding.queriesTitle.requestFocus();
                } else {
                    sendlink();
                }
            }
        });

        //Spinner for studentName
        final List<String> listStudentName=new ArrayList<String>();
        listStudentName.add("Select Your Name");

        ArrayAdapter<String> studentNameArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listStudentName);
        studentNameArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.queriesName.setAdapter(studentNameArrayAdapter);

        binding.queriesName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentName=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dbnameref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp :dataSnapshot.getChildren()){

                    StudentDetails br = dsp.getValue(StudentDetails.class);

                    hashMapstudentName.put(br.getStudentName(),br.getStudentAdmissionNumber());

                    listStudentName.add(br.getStudentName());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Spinner for StudentRollNumber
        final List<String> listStudentRollNumber=new ArrayList<String>();
        listStudentRollNumber.add("Select Roll Number");

        ArrayAdapter<String> rollNumberArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listStudentRollNumber);
        rollNumberArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.queriesRollNumber.setAdapter(rollNumberArrayAdapter);

        binding.queriesRollNumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentRollNumber=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dbrollref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp :dataSnapshot.getChildren()){

                    StudentDetails br = dsp.getValue(StudentDetails.class);

                    hashMapStudentRollNumber.put(br.getStudentRollNumber(),br.getStudentAdmissionNumber());

                    listStudentRollNumber.add(br.getStudentRollNumber());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void sendlink() {


        String queriesTitle = binding.queriesTitle.getEditText().getText().toString();
        Queries queries = new Queries(studentName, studentRollNumber, queriesTitle);

        reference.push().setValue(queries).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(QueriesActivity.this, "Queries sent to the Faculty", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(QueriesActivity.this, "Please, try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}