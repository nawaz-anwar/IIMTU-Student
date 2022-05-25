package com.coetusstudio.iimtustudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coetusstudio.iimtustudent.Adapter.LectureAdapter;
import com.coetusstudio.iimtustudent.Adapter.NotesAdapter;
import com.coetusstudio.iimtustudent.Model.Lecture;
import com.coetusstudio.iimtustudent.Model.Notes;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class NotesActivity extends AppCompatActivity {


    RecyclerView recviewNotes;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        recviewNotes=(RecyclerView)findViewById(R.id.rcNotes);
        recviewNotes.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Notes> options =
                new FirebaseRecyclerOptions.Builder<Notes>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("IIMTU").child("Notes"), Notes.class)
                        .build();

        notesAdapter=new NotesAdapter(options);
        recviewNotes.setAdapter(notesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        notesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        notesAdapter.stopListening();
    }
}