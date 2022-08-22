package com.coetusstudio.iimtustudent.Adapter;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coetusstudio.iimtustudent.Model.Notes;
import com.coetusstudio.iimtustudent.R;
import com.coetusstudio.iimtustudent.ViewpdfActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NotesAdapter extends FirebaseRecyclerAdapter<Notes,NotesAdapter.myviewholder>{


    public NotesAdapter(@NonNull FirebaseRecyclerOptions<Notes> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, int position, @NonNull final Notes Notes) {

        holder.header.setText(Notes.getFilename());

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.img1.getContext(), ViewpdfActivity.class);
                intent.putExtra("filename",Notes.getFilename());
                intent.putExtra("fileurl",Notes.getFileurl());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.img1.getContext().startActivity(intent);
            }
        });

        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.header.getContext(), ViewpdfActivity.class);
                intent.putExtra("filename",Notes.getFilename());
                intent.putExtra("fileurl",Notes.getFileurl());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.header.getContext().startActivity(intent);
            }
        });

        holder.notesDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("Notes").child("fileurl");
                //StorageReference islandRef = storageRef.child("images/island.jpg");

                storageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // Use the bytes to display the image
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });



            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerownotes,parent,false);
        return  new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1, notesDownload;
        TextView header;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            img1=itemView.findViewById(R.id.imgNotes);
            header=itemView.findViewById(R.id.notesTitle);
            notesDownload=itemView.findViewById(R.id.notesDownload);

        }
    }
}
