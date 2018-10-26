package com.ceribit.android.lazynotes;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ceribit.android.lazynotes.database.Note;

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

        TypedArray colorArray = mContext.getResources().obtainTypedArray(R.array.importance_colors);
        viewHolder.mTitleTextView.setText(currentNote.getTitle());
        viewHolder.mDescriptionTextView.setText(currentNote.getDescription());
        viewHolder.itemView.setBackgroundColor(
                        mContext.getResources().getColor
                                (colorArray.getResourceId(
                                currentNote.getImportanceLevel(),
                                Note.Importance.NEUTRAL.getImportanceLevel())));
        colorArray.recycle();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTitleTextView;
        TextView mDescriptionTextView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.grid_item_title);
            mDescriptionTextView = itemView.findViewById(R.id.grid_item_description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = ((MainActivity) mContext)
                            .getSupportFragmentManager();
                    NoteFragment noteFragment = new NoteFragment();
                    noteFragment.setArguments(createNoteBundle(getAdapterPosition()));
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_container, noteFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }

    private Bundle createNoteBundle(int position){
        Bundle bundle = new Bundle();
        bundle.putString(NoteFragment.TITLE_KEY, mNotes.get(position).getTitle());
        bundle.putString(NoteFragment.DESCRIPTION_KEY, mNotes.get(position).getDescription());
        return bundle;
    }
}
