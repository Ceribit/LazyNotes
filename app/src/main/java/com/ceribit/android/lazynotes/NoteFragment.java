package com.ceribit.android.lazynotes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ceribit.android.lazynotes.database.Note;
import com.ceribit.android.lazynotes.database.NotePresenter;

public class NoteFragment extends Fragment {

    public static String TITLE_KEY = "NOTE_TITLE";
    public static String DESCRIPTION_KEY = "NOTE_DESCRIPTION";
    public static String ID_KEY = "NOTE_ID";

    private Note mNote = null;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if(args != null){
            String noteTitle = args.getString(TITLE_KEY, "");
            String noteDescription = args.getString(DESCRIPTION_KEY, "");
            int noteId = args.getInt(ID_KEY, Note.NO_ID);
            mNote = new Note(noteTitle, noteDescription, 0);
            mNote.setId(noteId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.note_fragment, container, false);

        final EditText titleEditText = rootView.findViewById(R.id.fragment_note_title);
        final EditText descriptionEditText = rootView.findViewById(R.id.fragment_note_description);

        if(mNote != null){
            titleEditText.setText(mNote.getTitle());
            descriptionEditText.setText(mNote.getDescription());
        }

        Button saveButton = rootView.findViewById(R.id.btn_save_note);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("SaveButton", "\"Saved\"");
                getFragmentManager().popBackStack();
                Note newNote = new Note(
                        titleEditText.getText().toString(),
                        descriptionEditText.getText().toString(),
                        1
                        );
                if(mNote != null) {
                    Log.e("Update Note", "updated note called");
                    newNote.setId(mNote.getId());
                    NotePresenter.updateNote(getContext(), newNote);
                } else{
                    NotePresenter.addNote(getContext(), newNote);
                }
            }
        });

        Button deleteButton = rootView.findViewById(R.id.btn_delete_note);
        if(mNote == null){
            deleteButton.setVisibility(View.GONE);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNote != null){
                    getFragmentManager().popBackStack();

                    NotePresenter.deleteNote(getContext(), mNote.getId());
                }
            }
        });
        return rootView;
    }


}
