package com.ceribit.android.lazynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/* Main */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoteRecyclerViewFragment recyclerViewFragment = new NoteRecyclerViewFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, recyclerViewFragment)
                .addToBackStack(null)
                .commit();


    }
}
