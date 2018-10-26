package com.ceribit.android.lazynotes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ceribit.android.lazynotes.R;

public class NoteFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.note_fragment, container, false);

        TextView titleTextView = rootView.findViewById(R.id.fragment_note_title);
        TextView descriptionTextView = rootView.findViewById(R.id.fragment_note_description);

        titleTextView.setText("Default Text");
        descriptionTextView.setText(
                "Integer fermentum pellentesque aliquet. Vestibulum scelerisque, dolor et suscipit malesuada, ipsum libero egestas mauris, at tempor urna orci a nunc. Nam posuere eros leo, sit amet placerat arcu sagittis et. Nunc urna mauris, auctor eget porttitor ut, bibendum at massa. Morbi egestas ac arcu vel mattis. Proin venenatis augue placerat, consequat lorem in, mollis velit. Cras sed sem bibendum, blandit dolor finibus, pellentesque velit. Vivamus varius in dolor eu varius. Nullam a sodales ante."
        );

        return rootView;

    }
}
