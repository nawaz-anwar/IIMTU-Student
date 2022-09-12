package com.coetusstudio.iimtustudent.Activity.Notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.coetusstudio.iimtustudent.Activity.Home.MainActivity;
import com.coetusstudio.iimtustudent.Adapter.NotesAdapter;
import com.coetusstudio.iimtustudent.Model.Notes;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.mancj.materialsearchbar.MaterialSearchBar;

public class NotesActivity extends AppCompatActivity {


    RecyclerView recviewNotes;
    NotesAdapter notesAdapter;
    MaterialSearchBar search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotesActivity.this);
        recviewNotes=(RecyclerView)findViewById(R.id.rcNotes);
        recviewNotes.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        FirebaseRecyclerOptions<Notes> options =
                new FirebaseRecyclerOptions.Builder<Notes>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notes"), Notes.class)
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<Notes> options =
                new FirebaseRecyclerOptions.Builder<Notes>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notes").orderByChild("filename").startAt(s).endAt(s+"\uf8ff"), Notes.class)
                        .build();

        notesAdapter=new NotesAdapter(options);
        notesAdapter.startListening();
        recviewNotes.setAdapter(notesAdapter);

    }
}