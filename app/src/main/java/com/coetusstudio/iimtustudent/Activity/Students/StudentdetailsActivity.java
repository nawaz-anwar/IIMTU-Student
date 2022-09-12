package com.coetusstudio.iimtustudent.Activity.Students;

import static android.view.View.INVISIBLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Model.StudentDetails;
import com.coetusstudio.iimtustudent.R;
import com.coetusstudio.iimtustudent.databinding.ActivityStudentdetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentdetailsActivity extends AppCompatActivity {

    ActivityStudentdetailsBinding binding;
    FirebaseAuth auth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentdetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnStudentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentdetailsActivity.this.finish();
            }
        });

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Student Data");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dsp : snapshot.getChildren()) {
                    String image, name, email, rollNo, addNo, enrollNo, department, semester, section, grade, password;

                    try {
                        name = dsp.child(auth.getCurrentUser().getUid()).child("studentName").getValue(String.class);
                        binding.studentName.setText(name.toString());
                        email = dsp.child(auth.getCurrentUser().getUid()).child("studentEmail").getValue(String.class).toString();
                        binding.studentEmailId.setText(email);
                        rollNo = dsp.child(auth.getCurrentUser().getUid()).child("studentRollNumber").getValue(String.class).toString();
                        binding.studentRollNumber.setText(rollNo);
                        addNo = dsp.child(auth.getCurrentUser().getUid()).child("studentAdmissionNumber").getValue(String.class).toString();
                        binding.studentAdmissionNumber.setText(addNo);
                        enrollNo = dsp.child(auth.getCurrentUser().getUid()).child("studentEnrollmentNumber").getValue(String.class).toString();
                        binding.studentEnroolmentNumber.setText(enrollNo);
                        department = dsp.child(auth.getCurrentUser().getUid()).child("studentBranch").getValue(String.class).toString();
                        binding.studentBranch.setText(department);
                        semester = dsp.child(auth.getCurrentUser().getUid()).child("studentSemester").getValue(String.class).toString();
                        binding.studentSemester.setText(semester);
                        section = dsp.child(auth.getCurrentUser().getUid()).child("studentSection").getValue(String.class).toString();
                        binding.studentSection.setText(section);
                        grade = dsp.child(auth.getCurrentUser().getUid()).child("studentGrade").getValue(String.class).toString();
                        binding.studentGrade.setText(grade);
                        password = dsp.child(auth.getCurrentUser().getUid()).child("studentPassword").getValue(String.class).toString();
                        image = dsp.child(auth.getCurrentUser().getUid()).child("studentImage").getValue(String.class).toString();
                        Glide.with(getApplicationContext()).load(image).error(R.drawable.manimg).into(binding.studentImage);

                        binding.studentPasswordVisibility.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                binding.studentPassword.setText(password);
                                binding.studentPasswordVisibility.setVisibility(INVISIBLE);
                            }
                        });
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }


                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentdetailsActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}