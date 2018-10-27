package com.ceribit.android.lazynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ceribit.android.lazynotes.database.Note;

public class MainActivity extends AppCompatActivity {

    private NoteRecyclerViewFragment mContentFragment;

    private static String NOTE_TITLE_KEY = "noteTitle";
    private static String NOTE_DESCRIPTION_KEY = "noteDescription";
    private static String NOTE_ID_KEY = "noteId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        mContentFragment = new NoteRecyclerViewFragment();

        if(savedInstanceState != null){
            mContentFragment.startNoteUponCreation(getNoteFromBundle(savedInstanceState), true);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, mContentFragment)
                .addToBackStack(null)
                .commit();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Note note = mContentFragment.getClickedNote();
        if(note != null) {
            Log.e("Note", note.getDescription());
            outState.putString(NOTE_TITLE_KEY, note.getTitle());
            outState.putString(NOTE_DESCRIPTION_KEY, note.getDescription());
            outState.putInt(NOTE_ID_KEY, note.getId());
        }
        //outState.putString();
    }

    private Note getNoteFromBundle(Bundle bundle){
        String title = bundle.getString(NOTE_TITLE_KEY);
        String description = bundle.getString(NOTE_DESCRIPTION_KEY);
        int id = bundle.getInt(NOTE_ID_KEY);
        Note note = new Note(title, description, 1);
        note.setId(id);
        return note;
    }
}
