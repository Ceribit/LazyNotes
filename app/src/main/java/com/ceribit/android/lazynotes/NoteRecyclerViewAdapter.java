package com.ceribit.android.lazynotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ceribit.android.lazynotes.Database.Note;

import java.util.List;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {

    private List<Note> mNotes;
    private Context mContext;

    public NoteRecyclerViewAdapter(Context context, List<Note> notes) {
        super();
        mContext = context;
        mNotes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View newView = layoutInflater.inflate(R.layout.note_grid_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(newView);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Note currentNote = mNotes.get(position);

        viewHolder.mTitleTextView.setText(currentNote.getTitle());
        viewHolder.mDescriptionTextView.setText(currentNote.getDescription());
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitleTextView;
        TextView mDescriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.grid_item_title);
            mDescriptionTextView = itemView.findViewById(R.id.grid_item_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTitleTextView.setText("New Title");
                }
            });
        }
    }
}