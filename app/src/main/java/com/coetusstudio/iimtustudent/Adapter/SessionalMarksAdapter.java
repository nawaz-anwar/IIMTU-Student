package com.coetusstudio.iimtustudent.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Model.NoticeData;
import com.coetusstudio.iimtustudent.Model.SessionalMarks;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SessionalMarksAdapter extends FirebaseRecyclerAdapter<SessionalMarks,SessionalMarksAdapter.myviewholder> {


    public SessionalMarksAdapter(@NonNull FirebaseRecyclerOptions<SessionalMarks> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final SessionalMarksAdapter.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final SessionalMarks SessionalMarks)
    {


        holder.marksTitle.setText(SessionalMarks.getSessionalTitle());
        holder.sub1.setText(SessionalMarks.getSessionalSub1());
        holder.sub2.setText(SessionalMarks.getSessionalSub2());
        holder.sub3.setText(SessionalMarks.getSessionalSub3());
        holder.sub4.setText(SessionalMarks.getSessionalSub4());
        holder.sub5.setText(SessionalMarks.getSessionalSub5());
        holder.sub1Marks.setText(SessionalMarks.getSessionalMarks1());
        holder.sub2Marks.setText(SessionalMarks.getSessionalMarks2());
        holder.sub3Marks.setText(SessionalMarks.getSessionalMarks3());
        holder.sub4Marks.setText(SessionalMarks.getSessionalMarks4());
        holder.sub5Marks.setText(SessionalMarks.getSessionalMarks5());
        holder.maxMarks1.setText(SessionalMarks.getSessionalMaxMarks());
        holder.maxMarks2.setText(SessionalMarks.getSessionalMaxMarks());
        holder.maxMarks3.setText(SessionalMarks.getSessionalMaxMarks());
        holder.maxMarks4.setText(SessionalMarks.getSessionalMaxMarks());
        holder.maxMarks5.setText(SessionalMarks.getSessionalMaxMarks());
        holder.studentRollNumberSessional.setText(SessionalMarks.getStudentRollNo());
        holder.studentNameSessional.setText(SessionalMarks.getStudentName());


    } // End of OnBindViewMethod

    @NonNull
    @Override
    public SessionalMarksAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowsessional,parent,false);
        return new SessionalMarksAdapter.myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView marksTitle, sub1, sub2, sub3, sub4, sub5, sub1Marks, sub2Marks, sub3Marks, sub4Marks, sub5Marks, studentRollNumberSessional, studentNameSessional, maxMarks1, maxMarks2, maxMarks3, maxMarks4, maxMarks5;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            marksTitle=itemView.findViewById(R.id.marksTitle);
            sub1=itemView.findViewById(R.id.sub1);
            sub2=itemView.findViewById(R.id.sub2);
            sub3=itemView.findViewById(R.id.sub3);
            sub4=itemView.findViewById(R.id.sub4);
            sub5=itemView.findViewById(R.id.sub5);
            sub1Marks=itemView.findViewById(R.id.sub1Marks);
            sub2Marks=itemView.findViewById(R.id.sub2Marks);
            sub3Marks=itemView.findViewById(R.id.sub3Marks);
            sub4Marks=itemView.findViewById(R.id.sub4Marks);
            sub5Marks=itemView.findViewById(R.id.sub5Marks);
            maxMarks1=itemView.findViewById(R.id.sessionalMaxMarks1);
            maxMarks2=itemView.findViewById(R.id.sessionalMaxMarks2);
            maxMarks3=itemView.findViewById(R.id.sessionalMaxMarks3);
            maxMarks4=itemView.findViewById(R.id.sessionalMaxMarks4);
            maxMarks5=itemView.findViewById(R.id.sessionalMaxMarks5);
            studentRollNumberSessional=itemView.findViewById(R.id.studentRollNumberSessional);
            studentNameSessional=itemView.findViewById(R.id.studentNameSessional);

        }
    }


}
