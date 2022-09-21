package com.coetusstudio.iimtustudent.Activity.Notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.coetusstudio.iimtustudent.Activity.Home.MainActivity;
import com.coetusstudio.iimtustudent.Activity.Lecture.LectureActivity;
import com.coetusstudio.iimtustudent.Adapter.LectureAdapter;
import com.coetusstudio.iimtustudent.Adapter.NotesAdapter;
import com.coetusstudio.iimtustudent.Model.Lecture;
import com.coetusstudio.iimtustudent.Model.Notes;
import com.coetusstudio.iimtustudent.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mancj.materialsearchbar.MaterialSearchBar;

public class NotesActivity extends AppCompatActivity {


    RecyclerView recviewNotes;
    NotesAdapter notesAdapter;
    MaterialSearchBar search;
    String studentSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        setTitle("   Enter PDF");

        FirebaseDatabase.getInstance().getReference().child("Student Data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dsp : snapshot.getChildren()) {
                    try {
                        studentSection = dsp.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("studentSection").getValue(String.class).toString();

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NotesActivity.this);
                recviewNotes=(RecyclerView)findViewById(R.id.rcNotes);
                recviewNotes.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);

                FirebaseRecyclerOptions<Notes> options =
                        new FirebaseRecyclerOptions.Builder<Notes>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Notes").child(studentSection), Notes.class)
                                .build();

                notesAdapter=new NotesAdapter(options);
                recviewNotes.setAdapter(notesAdapter);
                notesAdapter.startListening();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NotesActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });


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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notes").child(studentSection).orderByChild("filename").startAt(s).endAt(s+"\uf8ff"), Notes.class)
                        .build();

        notesAdapter=new NotesAdapter(options);
        notesAdapter.startListening();
        recviewNotes.setAdapter(notesAdapter);

    }
}