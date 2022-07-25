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
import com.coetusstudio.iimtustudent.ImageViewActivity;
import com.coetusstudio.iimtustudent.Model.Notes;
import com.coetusstudio.iimtustudent.Model.NoticeData;
import com.coetusstudio.iimtustudent.R;
import com.coetusstudio.iimtustudent.ViewpdfActivity;
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


        holder.notificationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.notificationTitle.getContext(), ImageViewActivity.class);
                intent.putExtra("title",NoticeData.getTitle());
                intent.putExtra("imageurl",NoticeData.getImage());

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
