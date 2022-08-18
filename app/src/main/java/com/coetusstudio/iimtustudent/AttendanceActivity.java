package com.coetusstudio.iimtustudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {

    String sid,subid,subjectName;
    int tpresent = 0;
    int tattencount = 0;

    TextView subname;
    TextView atten_tv;
    TextView absent_tv;
    ListView listViewbydate;

    DatabaseReference attendancerecord,attendancerecbydate;
    ArrayList attendance= new ArrayList<>();
    ProgressBar mpercentprogressbarr;
    TextView mpercent_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        Intent intent=getIntent();
        sid=intent.getStringExtra("studentRollNumber");
        subid=intent.getStringExtra("studentSection");
        subjectName = intent.getStringExtra("subjectName");


        attendancerecord= FirebaseDatabase.getInstance().getReference().child("AttendenRecordSheet").child(subid).child(subjectName);
        attendancerecord.keepSynced(true);


        subname=findViewById(R.id.subname_tv);
        atten_tv=findViewById(R.id.attendence_tv);
        absent_tv=findViewById(R.id.absent_tv);
        mpercent_tv=findViewById(R.id.tv);

        listViewbydate=(ListView)findViewById(R.id.listviewbydate);

        //progress bar code
        subname.setText(subjectName);
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.circular);
        mpercentprogressbarr = findViewById(R.id.circularProgressbar);
        mpercentprogressbarr.setProgress(0);   // Main Progress
        mpercentprogressbarr.setSecondaryProgress(100); // Secondary Progress
        mpercentprogressbarr.setMax(100); // Maximum Progress
        mpercentprogressbarr.setProgressDrawable(drawable);



        attendancerecord.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    String aval;
                    aval = dsp.child(sid).child("atvalue").getValue(String.class);

                    try {
                        tpresent= tpresent+Integer.valueOf(aval.substring(0, 1));
                        tattencount += Integer.valueOf(aval.substring(2, 3));
                    }
                    catch (Exception e){}

                }
                int abs = tattencount - tpresent;
                float percent;
                percent = (((float) tpresent) / ((float) tattencount)) * 100;


                String clslvt=String.valueOf(tpresent-(abs*3));
                String attendence=(String.valueOf(tpresent)+"/"+String.valueOf(tattencount)) ;
                String absent=String.valueOf(abs);
                absent_tv.setText(absent);
                int mpercentint=(int)Math.round(percent) ;
                String mmper=String.valueOf(mpercentint)+"%";

                atten_tv.setText(attendence);
                mpercent_tv.setText(mmper);
                mpercentprogressbarr.setProgress(mpercentint);

                if(Float.valueOf(percent)<=75){
                    mpercent_tv.setTextColor(Color.RED);
                }
                else if(Float.valueOf(percent)>75 && Float.valueOf(percent)<85 ){
                    mpercent_tv.setTextColor(Color.BLUE);
                }
                else if(Float.valueOf(percent)>=85){
                    mpercent_tv.setTextColor(Color.MAGENTA);
                }
                else {
                }

                tpresent = 0;
                tattencount = 0;

                rec();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }

    public void rec(){


        attendancerecbydate= FirebaseDatabase.getInstance().getReference().child("AttendenRecordSheet").child(subid).child(subjectName);
        attendance.add("Date/Time"+"            "+"Attendance Value");
        attendancerecbydate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                attendance.clear();
                attendance.add("Date/Time"+"                "+"Attendance Value");
                for(DataSnapshot dspp :dataSnapshot.child(subid).getChildren()){
                    String datetime,avalue;
                    datetime=dspp.getKey();
                    avalue=dspp.child(sid).child("atvalue").getValue().toString();

                    attendance.add(datetime+"                   "+avalue);
                }
                listshow(attendance);//this is a function created by me
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    public void listshow(ArrayList attendancelist){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, attendancelist);
        listViewbydate.setAdapter(adapter);

    }
}