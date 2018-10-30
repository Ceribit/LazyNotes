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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ceribit.android.lazynotes.database.Note;
import com.ceribit.android.lazynotes.database.NotePresenter;
import com.ceribit.android.lazynotes.utils.SharedPreferencesManager;

public class NoteFragment extends Fragment {

    public static String TITLE_KEY = "NOTE_TITLE";
    public static String DESCRIPTION_KEY = "NOTE_DESCRIPTION";
    public static String ID_KEY = "NOTE_ID";
    public static String IMPORTANCE_KEY = "IMPORTANCE_KEY";

    private Note mNote = null;

    private EditText mTitleEditText;
    private EditText mDescriptionEditText;
    private RadioGroup mRadioGroupImportance;

    /* Testing */
    private static String LOG_TAG = NoteFragment.class.getSimpleName();

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if(args != null){
            String noteTitle = args.getString(TITLE_KEY, "");
            String noteDescription = args.getString(DESCRIPTION_KEY, "");
            int importanceLevel = args.getInt(IMPORTANCE_KEY, 1);
            int noteId = args.getInt(ID_KEY, Note.NO_ID);
            mNote = new Note(noteTitle, noteDescription, importanceLevel);
            mNote.setId(noteId);
            Log.e(LOG_TAG, "Stored ID upon storing args" + mNote.getId());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(LOG_TAG, "NoteFragment created");

        final View rootView = inflater.inflate(R.layout.note_fragment, container, false);

        mTitleEditText = rootView.findViewById(R.id.fragment_note_title);
        mDescriptionEditText = rootView.findViewById(R.id.fragment_note_description);
        mRadioGroupImportance = rootView.findViewById(R.id.radio_grp_importance);

        if(SharedPreferencesManager.isNoteClicked() &&
                SharedPreferencesManager.getNote().getId() != -1){
            mNote = SharedPreferencesManager.getNote();
        }

        if(mNote != null){
            mTitleEditText.setText(mNote.getTitle());
            mDescriptionEditText.setText(mNote.getDescription());
            ((RadioButton)mRadioGroupImportance.getChildAt(mNote.getImportanceLevel())).setChecked(true);
        }

        Button saveButton = rootView.findViewById(R.id.btn_save_note);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
                Note newNote = new Note(
                        mTitleEditText.getText().toString(),
                        mDescriptionEditText.getText().toString(),
                        getImportanceFromRadioGroup()
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

    public Note getCurrentNote(){
        String title = mTitleEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();
        Note currentNote = new Note(title, description, getImportanceFromRadioGroup());
        if(mNote != null){
            currentNote.setId(mNote.getId());
        }
        return currentNote;
    }

    private int getImportanceFromRadioGroup(){
        if(mRadioGroupImportance == null){
            return 1;
        } else{
            int id = mRadioGroupImportance.getCheckedRadioButtonId();
            switch(id){
                case R.id.radio_btn_1:
                    return 0;
                case R.id.radio_btn_2:
                    return 1;
                case R.id.radio_btn_3:
                    return 2;
                case R.id.radio_btn_4:
                    return 3;
                default:
                    return 1;
            }
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null){
            //setArguments(savedInstanceState);
        }
        Log.e(LOG_TAG, "onActivityCreated call");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(mNote != null) {
            Note newNote = getCurrentNote();
            newNote.setId(mNote.getId());
            Log.e(LOG_TAG, "SaveInstance mNote ID: " + mNote.getId());
            SharedPreferencesManager.storeNote(newNote);
            SharedPreferencesManager.setNoteClicked(true);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        if(mNote != null){
            Log.e(LOG_TAG, "Pause mNote ID: " + mNote.getId());
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(LOG_TAG, "NoteFragment destroyed");
    }


}
