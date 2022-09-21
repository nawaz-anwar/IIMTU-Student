package com.coetusstudio.iimtustudent.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Activity.Notification.ImageViewActivity;
import com.coetusstudio.iimtustudent.Model.NoticeData;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoticeAdapter extends FirebaseRecyclerAdapter<NoticeData,NoticeAdapter.myviewholder> {


    public NoticeAdapter(@NonNull FirebaseRecyclerOptions<NoticeData> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final NoticeAdapter.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final NoticeData NoticeData)
    {


        holder.notificationTitle.setText(NoticeData.getTitle());
        holder.notificationDate.setText(NoticeData.getDate());
        holder.notificationTime.setText(NoticeData.getTime());
        Glide.with(holder.notificationFacultyImage.getContext()).load(NoticeData.getFacultyImage())
                .placeholder(R.drawable.manimg)
                .circleCrop()
                .error(R.drawable.manimg)
                .into(holder.notificationFacultyImage);


        holder.notificationTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.notificationTitle.getContext(), ImageViewActivity.class);
                intent.putExtra("title",NoticeData.getTitle());
                intent.putExtra("imageurl",NoticeData.getImage());
                intent.putExtra("date",NoticeData.getDate());
                intent.putExtra("time",NoticeData.getTime());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.notificationTitle.getContext().startActivity(intent);


            }
        });

        holder.btnLectureNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.notificationTitle.getContext(), ImageViewActivity.class);
                intent.putExtra("title",NoticeData.getTitle());
                intent.putExtra("imageurl",NoticeData.getImage());
                intent.putExtra("date",NoticeData.getDate());
                intent.putExtra("time",NoticeData.getTime());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.notificationTitle.getContext().startActivity(intent);


            }
        });


    } // End of OnBindViewMethod

    @NonNull
    @Override
    public NoticeAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerownotice,parent,false);
        return new NoticeAdapter.myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView notificationTitle, notificationDate, notificationTime;
        CircleImageView notificationFacultyImage;
        ImageView btnLectureNext;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            notificationTitle=itemView.findViewById(R.id.notificationTitle);
            notificationDate=itemView.findViewById(R.id.notificationDate);
            notificationTime=itemView.findViewById(R.id.notificationTime);
            notificationFacultyImage=itemView.findViewById(R.id.notificationFacultyImage);
            btnLectureNext=itemView.findViewById(R.id.btnLectureNext);

        }
    }
}
