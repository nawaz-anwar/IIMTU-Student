package com.coetusstudio.iimtustudent.Activity.Queries;

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
import com.coetusstudio.iimtustudent.R;
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
import java.util.List;

public class QueriesActivity extends AppCompatActivity {

    ActivityQueriesBinding binding;
    DatabaseReference reference, dbfacultyref;
    String studentName, studentRollNumber, facultyName, studentImage, studentSection;
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
        dbfacultyref = FirebaseDatabase.getInstance().getReference().child("Faculty Data");

        FirebaseDatabase.getInstance().getReference().child("Student Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dsp : snapshot.getChildren()) {
                    try {
                        studentSection = dsp.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("studentSection").getValue(String.class).toString();
                        studentName = dsp.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("studentName").getValue(String.class).toString();
                        studentImage = dsp.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("studentImage").getValue(String.class).toString();
                        studentRollNumber = dsp.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("studentRollNumber").getValue(String.class).toString();


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                //Spinner for facultyName
                final List<String> listFacultyName=new ArrayList<String>();
                listFacultyName.add("Select Faculty Name");

                ArrayAdapter<String> facultyNameArrayAdapter=new ArrayAdapter<String>(QueriesActivity.this,android.R.layout.simple_spinner_item,listFacultyName);
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

                dbfacultyref.child(studentSection).addListenerForSingleValueEvent(new ValueEventListener() {
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

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QueriesActivity.this);
                recviewQueries=(RecyclerView)findViewById(R.id.rcQueries);
                recviewQueries.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                FirebaseRecyclerOptions<Queries> options =
                        new FirebaseRecyclerOptions.Builder<Queries>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Resolve Queries").child(studentSection).child(studentRollNumber), Queries.class)
                                .build();
                queriesAdapter=new QueriesAdapter(options);
                recviewQueries.setAdapter(queriesAdapter);
                queriesAdapter.startListening();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QueriesActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


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

    }
    private void sendlink() {

        String queriesTitle = binding.queriesTitle.getEditText().getText().toString();
        Queries queries = new Queries(studentName, studentRollNumber, facultyName, queriesTitle, studentImage);

        reference.child(studentSection).child(facultyName).push().setValue(queries).addOnSuccessListener(new OnSuccessListener<Void>() {
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