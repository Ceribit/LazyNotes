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

    private Note mNote;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if(args != null){
            String noteTitle = args.getString(TITLE_KEY, "Add title here...");
            String noteDescription = args.getString(DESCRIPTION_KEY, "...");
            mNote = new Note(noteTitle, noteDescription, 0);
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
        } else {
            titleEditText.setText("Default Text");
            descriptionEditText.setText(
                    "Integer fermentum pellentesque aliquet. Vestibulum scelerisque, dolor et suscipit malesuada, ipsum libero egestas mauris, at tempor urna orci a nunc. Nam posuere eros leo, sit amet placerat arcu sagittis et. Nunc urna mauris, auctor eget porttitor ut, bibendum at massa. Morbi egestas ac arcu vel mattis. Proin venenatis augue placerat, consequat lorem in, mollis velit. Cras sed sem bibendum, blandit dolor finibus, pellentesque velit. Vivamus varius in dolor eu varius. Nullam a sodales ante."
            );
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
                        0
                        );
                NotePresenter.addNote(getContext(), newNote);
            }
        });
        return rootView;
    }


}
