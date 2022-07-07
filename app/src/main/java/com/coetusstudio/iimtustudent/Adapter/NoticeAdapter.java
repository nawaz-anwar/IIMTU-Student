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
import com.coetusstudio.iimtustudent.Model.NoticeData;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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
        Glide.with(holder.notificationImage.getContext()).load(NoticeData.getImage())
                .placeholder(R.drawable.manimg)
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
        TextView notificationTitle, notificationDate, notificationTime;
        ImageView notificationImage;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            notificationTitle=itemView.findViewById(R.id.notificationTitle);
            notificationImage=itemView.findViewById(R.id.notificationImage);
            notificationDate=itemView.findViewById(R.id.notificationDate);
            notificationTime=itemView.findViewById(R.id.notificationTime);

        }
    }
}
