package com.coetusstudio.iimtustudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.coetusstudio.iimtustudent.databinding.ActivityStudentdetailsBinding;

public class StudentdetailsActivity extends AppCompatActivity {

    ActivityStudentdetailsBinding binding;

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
    }
}