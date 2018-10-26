package com.ceribit.android.lazynotes;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ceribit.android.lazynotes.database.Note;
import com.ceribit.android.lazynotes.database.NotePresenter;

import java.util.ArrayList;

public class NoteRecyclerViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.note_recycler_view_layout, container,false);
        RecyclerView recyclerView = rootView.findViewById(R.id.note_recycler_view);

        ArrayList<Note> notesList = NotePresenter.getAllNotes(getContext());

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.fab_add_note);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =
                        ((MainActivity) getContext()).getSupportFragmentManager();
                if(fragmentManager != null){
                    NoteFragment noteFragment = new NoteFragment();
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_container, noteFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
        NoteRecyclerViewAdapter adapter = new NoteRecyclerViewAdapter(getContext(), notesList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(getActivity() != null) {
            getActivity().finish();
        }
    }
}
