package com.coetusstudio.iimtustudent.Adapter;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Model.SessionalMarks;
import com.coetusstudio.iimtustudent.R;

import java.util.ArrayList;

public class SessionalMarksAdapter extends RecyclerView.Adapter<SessionalMarksAdapter.myviewholder> {

    Context context;
    ArrayList<SessionalMarks> list;
    String studentName;

    public SessionalMarksAdapter(Context context, ArrayList<SessionalMarks> list, String studentName) {
        this.context = context;
        this.list = list;
        this.studentName = studentName;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowsessional,parent,false);
        return new SessionalMarksAdapter.myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {

        SessionalMarks sessionalMarks = list.get(position);

        holder.marksTitle.setText(sessionalMarks.getSessionalTitle());
        holder.sub1.setText(sessionalMarks.getSessionalSub1());
        holder.sub2.setText(sessionalMarks.getSessionalSub2());
        holder.sub3.setText(sessionalMarks.getSessionalSub3());
        holder.sub4.setText(sessionalMarks.getSessionalSub4());
        holder.sub5.setText(sessionalMarks.getSessionalSub5());
        holder.sub1Marks.setText(sessionalMarks.getSessionalMarks1());
        holder.sub2Marks.setText(sessionalMarks.getSessionalMarks2());
        holder.sub3Marks.setText(sessionalMarks.getSessionalMarks3());
        holder.sub4Marks.setText(sessionalMarks.getSessionalMarks4());
        holder.sub5Marks.setText(sessionalMarks.getSessionalMarks5());
        holder.maxMarks1.setText(sessionalMarks.getSessionalMaxMarks());
        holder.maxMarks2.setText(sessionalMarks.getSessionalMaxMarks());
        holder.maxMarks3.setText(sessionalMarks.getSessionalMaxMarks());
        holder.maxMarks4.setText(sessionalMarks.getSessionalMaxMarks());
        holder.maxMarks5.setText(sessionalMarks.getSessionalMaxMarks());
        holder.studentRollNumberSessional.setText(sessionalMarks.getStudentRollNo());
        holder.studentNameSessional.setText(studentName);

    }

    @Override
    public int getItemCount() {
        return list.size();
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
