package com.coetusstudio.iimtustudent.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coetusstudio.iimtustudent.Model.Lecture;
import com.coetusstudio.iimtustudent.Model.Notes;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NotesAdapter extends FirebaseRecyclerAdapter<Notes,NotesAdapter.myviewholder> {


    public NotesAdapter(@NonNull FirebaseRecyclerOptions<Notes> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final NotesAdapter.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Notes Notes)
    {


        holder.filename.setText(Notes.getFilename());
        holder.fileurl.setText(Notes.getFileurl());


    } // End of OnBindViewMethod

    @NonNull
    @Override
    public NotesAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerownotes,parent,false);
        return new NotesAdapter.myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView filename, fileurl;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            filename=itemView.findViewById(R.id.notesTitle);
            fileurl=itemView.findViewById(R.id.imgNotes);

        }
    }
}
