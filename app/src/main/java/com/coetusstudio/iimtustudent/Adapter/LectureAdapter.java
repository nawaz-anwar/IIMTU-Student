package com.coetusstudio.iimtustudent.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Model.Lecture;
import com.coetusstudio.iimtustudent.R;
import com.coetusstudio.iimtustudent.ViewpdfActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class LectureAdapter extends FirebaseRecyclerAdapter<Lecture,LectureAdapter.myviewholder>{


    public LectureAdapter(@NonNull FirebaseRecyclerOptions<Lecture> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final LectureAdapter.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Lecture Lecture)
    {


        holder.lectureName.setText(Lecture.getLectureName());
        holder.lectureTiming.setText(Lecture.getLectureTiming());
        holder.lectureLink.setText(Lecture.getLectureLink());
        holder.lectureDate.setText(Lecture.getLectureDate());
        holder.lectureTime.setText(Lecture.getLectureTime());
        Glide.with(holder.lectureFacultyImage.getContext()).load(Lecture.getFacultyImage())
                .placeholder(R.drawable.manimg)
                .circleCrop()
                .error(R.drawable.manimg)
                .into(holder.lectureFacultyImage);
        String uriLink = Lecture.getLectureLink();

        holder.btnLectureJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                Intent chooser = Intent.createChooser(intent, uriLink);

                try {
                    holder.lectureLink.getContext().startActivity(chooser);
                } catch (ActivityNotFoundException e) {
                    // Define what your app should do if no activity can handle the intent.
                }
            }
        });


    } // End of OnBindViewMethod

    @NonNull
    @Override
    public LectureAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowlecture,parent,false);
        return new LectureAdapter.myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView lectureName, lectureTiming, lectureLink, lectureDate, lectureTime;
        Button btnLectureJoin;
        CircleImageView lectureFacultyImage;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            lectureName=itemView.findViewById(R.id.lectureName);
            lectureTiming=itemView.findViewById(R.id.lectureTiming);
            lectureLink=itemView.findViewById(R.id.lectureLink);
            lectureDate=itemView.findViewById(R.id.lectureDate);
            lectureTime=itemView.findViewById(R.id.lectureTime);
            btnLectureJoin=itemView.findViewById(R.id.btnLectureJoin);
            lectureFacultyImage=itemView.findViewById(R.id.lectureFacultyImage);

        }
    }

}
