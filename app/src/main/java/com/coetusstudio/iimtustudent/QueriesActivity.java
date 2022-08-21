package com.coetusstudio.iimtustudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.coetusstudio.iimtustudent.Adapter.QueriesAdapter;
import com.coetusstudio.iimtustudent.Model.AddFaculty;
import com.coetusstudio.iimtustudent.Model.Queries;
import com.coetusstudio.iimtustudent.Model.StudentDetails;
import com.coetusstudio.iimtustudent.databinding.ActivityQueriesBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    DatabaseReference dbnameref, dbrollref, dbfacultyref, dbfacultyuid;
    String studentName, studentRollNumber, facultyName;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    RecyclerView recviewQueries;
    QueriesAdapter queriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQueriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ProgressDialog progressDialog = new ProgressDialog(QueriesActivity.this);
        progressDialog.setTitle("Queries");
        progressDialog.setMessage("Queries Sending to Faculty...");

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Queries");
        dbrollref = FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Student");
        dbnameref = FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Student");
        dbfacultyref = FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Faculty");
        dbfacultyuid = FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Faculty");

        binding.btnSendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                if (binding.queriesTitle.getEditText().getText().toString().isEmpty()) {
                    binding.queriesTitle.setError("Empty");
                    binding.queriesTitle.requestFocus();
                } else {
                    sendlink();
                    progressDialog.dismiss();
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

        dbnameref.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    listStudentName.add(dataSnapshot.child("studentName").getValue().toString());

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

        dbrollref.child(currentUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    listStudentRollNumber.add(dataSnapshot.child("studentRollNumber").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Spinner for facultyName
        final List<String> listFacultyName=new ArrayList<String>();
        listFacultyName.add("Select Faculty Name");

        ArrayAdapter<String> facultyNameArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listFacultyName);
        facultyNameArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.queriesFaculty.setAdapter(facultyNameArrayAdapter);

        binding.queriesFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                facultyName=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dbfacultyref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dsp :dataSnapshot.getChildren()){

                    AddFaculty br = dsp.getValue(AddFaculty.class);



                    listFacultyName.add(br.getFacultyName());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Student").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    String queriesStudentRollNumber = snapshot.child("studentRollNumber").getValue().toString();

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QueriesActivity.this);
                    recviewQueries=(RecyclerView)findViewById(R.id.rcQueries);
                    recviewQueries.setLayoutManager(linearLayoutManager);
                    linearLayoutManager.setReverseLayout(true);
                    linearLayoutManager.setStackFromEnd(true);
                    FirebaseRecyclerOptions<Queries> options =
                            new FirebaseRecyclerOptions.Builder<Queries>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Resolve Queries").child(queriesStudentRollNumber), Queries.class)
                                    .build();
                    queriesAdapter=new QueriesAdapter(options);
                    recviewQueries.setAdapter(queriesAdapter);
                    queriesAdapter.startListening();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void sendlink() {

        String queriesTitle = binding.queriesTitle.getEditText().getText().toString();
        Queries queries = new Queries(studentName, studentRollNumber, facultyName, queriesTitle);

        reference.child(facultyName).push().setValue(queries).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(QueriesActivity.this, "Queries Sent...", Toast.LENGTH_SHORT).show();

                binding.queriesTitle.getEditText().setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(QueriesActivity.this, "Please, try again later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        queriesAdapter.stopListening();
    }
}