package com.coetusstudio.iimtustudent.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.coetusstudio.iimtustudent.Model.Notice;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NoticeAdapter extends FirebaseRecyclerAdapter<Notice,NoticeAdapter.myviewholder> {


    public NoticeAdapter(@NonNull FirebaseRecyclerOptions<Notice> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final NoticeAdapter.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Notice Notice)
    {


        holder.notificationTitle.setText(Notice.getNotificationTitle());
        holder.notificationDate.setText(Notice.getNotificationDate());
        holder.notificationTiming.setText(Notice.getNotificationTiming());
        Glide.with(holder.notificationImage.getContext()).load(Notice.getNotificationImage())
                .placeholder(R.drawable.manimg)
                .circleCrop()
                .error(R.drawable.manimg)
                .into(holder.notificationImage);


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
        TextView notificationTitle, notificationDate, notificationTiming;
        ImageView notificationImage;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            notificationTitle=itemView.findViewById(R.id.notesTitle);
            notificationImage=itemView.findViewById(R.id.notificationImage);
            notificationDate=itemView.findViewById(R.id.notificationDate);
            notificationTiming=itemView.findViewById(R.id.notificationTiming);

        }
    }
}
