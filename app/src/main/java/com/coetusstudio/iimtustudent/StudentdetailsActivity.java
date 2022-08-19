package com.coetusstudio.iimtustudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Model.StudentDetails;
import com.coetusstudio.iimtustudent.databinding.ActivityStudentdetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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
        databaseReference = FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Student").child(auth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                StudentDetails studentDetails = snapshot.getValue(StudentDetails.class);

                binding.studentName.setText(studentDetails.getStudentName());
                binding.studentEmailId.setText(studentDetails.getStudentEmail());
                binding.studentRollNumber.setText(studentDetails.getStudentRollNumber());
                binding.studentAdmissionNumber.setText(studentDetails.getStudentAdmissionNumber());
                binding.studentEnroolmentNumber.setText(studentDetails.getStudentEnrollmentNumber());
                binding.studentRollNumber.setText(studentDetails.getStudentRollNumber());
                binding.studentGrade.setText(studentDetails.getStudentGrade());
                binding.studentSemester.setText(studentDetails.getStudentSemester());
                binding.studentSection.setText(studentDetails.getStudentSection());
                //binding.studentAttendance.setText(studentDetails.getStudentAttendance());
                String url = snapshot.child("studentImage").getValue().toString();
                Glide.with(getApplicationContext()).load(url).error(R.drawable.manimg).into(binding.studentImage);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudentdetailsActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}