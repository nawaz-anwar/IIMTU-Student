package com.coetusstudio.iimtustudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.coetusstudio.iimtustudent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView lecture, studentDetails, attendance, receivedNotification, chat, payment, notes;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        attendance = findViewById(R.id.attendance);
        payment = findViewById(R.id.payment);
        receivedNotification = findViewById(R.id.receivedNotification);
        lecture = findViewById(R.id.lecture);
        studentDetails = findViewById(R.id.studentDetails);
        notes = findViewById(R.id.notes);
        //syllabus = findViewById(R.id.syllabus);
        chat = findViewById(R.id.chat);

        attendance.setOnClickListener(this);
        payment.setOnClickListener(this);
        receivedNotification.setOnClickListener(this);
        lecture.setOnClickListener(this);
        studentDetails.setOnClickListener(this);
        notes.setOnClickListener(this);
        //syllabus.setOnClickListener(this);
        chat.setOnClickListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        toggle.syncState();

        setSupportActionBar(binding.appBarMain.toolbar);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.attendance:
                Intent intent = new Intent(MainActivity.this, AttendanceActivity.class);
                startActivity(intent);
                break;
            case R.id.receivedNotification:
                Intent intent1 = new Intent(MainActivity.this, NoticeActivity.class);
                startActivity(intent1);
                break;
            case R.id.syllabus:
                Intent intent2 = new Intent(MainActivity.this, SyllabusActivity.class);
                startActivity(intent2);
                break;
            case R.id.lecture:
                Intent intent3 = new Intent(MainActivity.this, LectureActivity.class);
                startActivity(intent3);
            case R.id.studentDetails:
                Intent intent4 = new Intent(MainActivity.this, StudentdetailsActivity.class);
                startActivity(intent4);
                break;
            case R.id.notes:
                Intent intent5 = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent5);
                break;
            case R.id.payment:
                Intent intent6 = new Intent(MainActivity.this, PaymentActivity.class);
                startActivity(intent6);
                break;
            case R.id.chat:
                Intent intent7 = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(intent7);
                break;
        }
    }
}