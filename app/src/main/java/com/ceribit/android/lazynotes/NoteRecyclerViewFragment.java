package com.ceribit.android.lazynotes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ceribit.android.lazynotes.Database.Note;

import java.util.ArrayList;

public class NoteRecyclerViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.note_recycler_view_layout, container,false);
        RecyclerView recyclerView = rootView.findViewById(R.id.note_recycler_view);

        ArrayList<Note> notesList = new ArrayList<>();
        for(int i = 0; i < 200; i++){
            notesList.add(
                    new Note("title", "description", 0)
            );
        }

        NoteRecyclerViewAdapter adapter = new NoteRecyclerViewAdapter(getContext(), notesList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        return rootView;
    }
}
