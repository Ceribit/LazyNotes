package com.ceribit.android.lazynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ceribit.android.lazynotes.utils.SharedPreferencesManager;

public class MainActivity extends AppCompatActivity {

    private NoteRecyclerViewFragment mContentFragment;

    private static String NOTE_TITLE_KEY = "noteTitle";
    private static String NOTE_DESCRIPTION_KEY = "noteDescription";
    private static String NOTE_ID_KEY = "noteId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferencesManager.init(this);

        setContentView(R.layout.activity_main);
        mContentFragment = new NoteRecyclerViewFragment();

        if(SharedPreferencesManager.isNoteClicked()){
            mContentFragment.restoreSelectedNote(SharedPreferencesManager.getNote(), true);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, mContentFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
