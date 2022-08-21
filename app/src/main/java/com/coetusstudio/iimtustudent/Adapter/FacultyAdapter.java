package com.coetusstudio.iimtustudent.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Model.AddFaculty;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyAdapter extends FirebaseRecyclerAdapter<AddFaculty,FacultyAdapter.myviewholder> {

    public FacultyAdapter(@NonNull FirebaseRecyclerOptions<AddFaculty> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FacultyAdapter.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final AddFaculty AddFaculty)
    {


        holder.name.setText(AddFaculty.getFacultyName());
        holder.email.setText(AddFaculty.getFacultyEmail());
        holder.id.setText(AddFaculty.getFacultyId());
        holder.subject.setText(AddFaculty.getFacultySubject());
        holder.subjectCode.setText(AddFaculty.getFacultySubjectCode());
        Glide.with(holder.img.getContext()).load(AddFaculty.getFacultyImage())
                .placeholder(R.drawable.manimg)
                .circleCrop()
                .error(R.drawable.manimg)
                .into(holder.img);


    } // End of OnBindViewMethod

    @NonNull
    @Override
    public FacultyAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowfaculty,parent,false);
        return new FacultyAdapter.myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        ImageView edit,delete;
        TextView name,email,id,subject,subjectCode,admission,enrollment,roll,fees,semester,grade,attendance;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img=itemView.findViewById(R.id.facultyRcImage);
            name=itemView.findViewById(R.id.facultyRcName);
            email=itemView.findViewById(R.id.facultyRcEmail);
            id=itemView.findViewById(R.id.facultyRcId);
            subject=itemView.findViewById(R.id.facultyRcSubjectName);
            subjectCode=itemView.findViewById(R.id.facultyRcSubjectCode);
        }
    }

}
