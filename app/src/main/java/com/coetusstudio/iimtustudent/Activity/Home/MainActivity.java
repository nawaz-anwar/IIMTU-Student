package com.coetusstudio.iimtustudent.Activity.Home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Activity.Attendance.SelectSubjectAttendance;
import com.coetusstudio.iimtustudent.Activity.Faculty.FacultyDeatilsActivity;
import com.coetusstudio.iimtustudent.Activity.Lecture.LectureActivity;
import com.coetusstudio.iimtustudent.Activity.Students.StudentdetailsActivity;
import com.coetusstudio.iimtustudent.Activity.Notes.NotesActivity;
import com.coetusstudio.iimtustudent.Activity.Notification.NotificationActivity;
import com.coetusstudio.iimtustudent.Activity.Queries.QueriesActivity;
import com.coetusstudio.iimtustudent.R;
import com.coetusstudio.iimtustudent.Activity.Marks.Sessional_Assignment_Marks;
import com.coetusstudio.iimtustudent.VerifiyActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.coetusstudio.iimtustudent.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView lecture, studentDetails, attendance, receivedNotification, marks, facultyDetails, notes, queries;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private View header;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton emailFeedback;
    Toolbar toolbar;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    CircleImageView studentImageProfile;
    TextView studentNameProfile, studentEmailIdProfile, studentRollNumberProfile, studentAdmissionNumberProfile;
    String confirmEmail, section, rollNumber, studentName, studentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();

        Intent intent = getIntent();
        confirmEmail = intent.getStringExtra("confirmEmail");

        studentNameProfile = findViewById(R.id.studentNameProfile);
        studentImageProfile = findViewById(R.id.studentImageProfile);
        studentEmailIdProfile = findViewById(R.id.studentEmailIdProfile);


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        emailFeedback = findViewById(R.id.emailFeedback);

        attendance = findViewById(R.id.attendance);
        facultyDetails = findViewById(R.id.facultyDetails);
        receivedNotification = findViewById(R.id.receivedNotification);
        lecture = findViewById(R.id.lecture);
        studentDetails = findViewById(R.id.studentDetails);
        notes = findViewById(R.id.notes);
        queries = findViewById(R.id.queries);
        marks = findViewById(R.id.marks);



        attendance.setOnClickListener(this);
        facultyDetails.setOnClickListener(this);
        receivedNotification.setOnClickListener(this);
        lecture.setOnClickListener(this);
        studentDetails.setOnClickListener(this);
        notes.setOnClickListener(this);
        queries.setOnClickListener(this);
        marks.setOnClickListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        toggle.syncState();
        toolbar.setTitle("IIMTU Student");

        updateNavHeader(confirmEmail);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                if (id == R.id.profile) {
                    Intent intent = new Intent(MainActivity.this, StudentdetailsActivity.class);
                    startActivity(intent);

                } else if (id == R.id.profileSendNotification) {
                    Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                    startActivity(intent);

                } else if (id == R.id.privacyPolicy) {
                    Uri webpage = Uri.parse("https://www.iimtu.com/privacy-policy");
                    Intent webMeet = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(webMeet);
                } else if (id == R.id.aboutUs) {
                    Uri webpage = Uri.parse("https://www.iimtu.com/about-iimt/our-founder");
                    Intent webMeet = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(webMeet);
                }else if (id == R.id.resetPassword) {
                    startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));

                }else if (id == R.id.logout) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        emailFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"feedback.coetusstudio@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "IIMTU Student Feedback");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Type your query here...");
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                startActivity(emailIntent);
            }
        });



    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.attendance:
                Intent intent = new Intent(MainActivity.this, SelectSubjectAttendance.class);
                startActivity(intent);
                break;
            case R.id.receivedNotification:
                Intent intent1 = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent1);
                break;
            case R.id.marks:
                Intent intent2 = new Intent(MainActivity.this, Sessional_Assignment_Marks.class);
                intent2.putExtra("section", section);
                intent2.putExtra("rollNumber", rollNumber);
                intent2.putExtra("name", studentName);
                intent2.putExtra("image", studentImage);
                startActivity(intent2);
                break;
            case R.id.lecture:
                Intent intent3 = new Intent(MainActivity.this, LectureActivity.class);
                startActivity(intent3);
                break;
            case R.id.studentDetails:
                Intent intent4 = new Intent(MainActivity.this, StudentdetailsActivity.class);
                startActivity(intent4);
                break;
            case R.id.notes:
                Intent intent5 = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent5);
                break;
            case R.id.facultyDetails:
                Intent intent6 = new Intent(MainActivity.this, FacultyDeatilsActivity.class);
                startActivity(intent6);
                break;
            case R.id.queries:
                Intent intent7 = new Intent(MainActivity.this, QueriesActivity.class);
                startActivity(intent7);
                break;
        }
    }

    public void updateNavHeader(String confirmEmail) {

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.studentNameProfile);
        TextView navUserMail = headerView.findViewById(R.id.studentEmailIdProfile);
        TextView navUserRoll = headerView.findViewById(R.id.studentRollNumberProfile);
        ImageView navUserPhot = headerView.findViewById(R.id.studentImageProfile);


        FirebaseDatabase.getInstance().getReference().child("Student Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dsp : snapshot.getChildren()) {
                    String email = null;

                    try {
                        studentName = dsp.child(auth.getCurrentUser().getUid()).child("studentName").getValue(String.class).toString();
                        navUsername.setText(studentName);
                        email = dsp.child(auth.getCurrentUser().getUid()).child("studentEmail").getValue(String.class).toString();
                        navUserMail.setText(email);
                        rollNumber = dsp.child(auth.getCurrentUser().getUid()).child("studentRollNumber").getValue(String.class).toString();
                        navUserRoll.setText(rollNumber);
                        section = dsp.child(auth.getCurrentUser().getUid()).child("studentSection").getValue(String.class).toString();

                        studentImage = dsp.child(auth.getCurrentUser().getUid()).child("studentImage").getValue(String.class).toString();
                        Glide.with(getApplicationContext()).load(studentImage).error(R.drawable.manimg).into(navUserPhot);

                        if (email.equals(confirmEmail)){

                        }else {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }



                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
            }

}