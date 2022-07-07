package com.coetusstudio.iimtustudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.coetusstudio.iimtustudent.Model.Queries;
import com.coetusstudio.iimtustudent.databinding.ActivityQueriesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QueriesActivity extends AppCompatActivity {

    ActivityQueriesBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQueriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference().child("Queries");

        binding.btnSendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.queriesName.getEditText().getText().toString().isEmpty()) {
                    binding.queriesName.setError("Empty");
                    binding.queriesName.requestFocus();
                } else {
                    sendlink();
                }
            }
        });

    }
    private void sendlink() {

        String queriesName = binding.queriesName.getEditText().getText().toString();
        String queriesRollNumber = binding.queriesRollNumber.getEditText().getText().toString();
        String queriesTitle = binding.queriesTitle.getEditText().getText().toString();
        Queries queries = new Queries(queriesName, queriesRollNumber, queriesTitle);

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