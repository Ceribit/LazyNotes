package com.ceribit.android.lazynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ceribit.android.lazynotes.Database.Note;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.note_recycler_view);
        ArrayList<Note> notesList = new ArrayList<>();
        for(int i = 0; i < 200; i++){
            notesList.add(
                    new Note("title", "description", 0)
            );
        }
        NoteRecyclerViewAdapter adapter = new NoteRecyclerViewAdapter(this, notesList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }
}
