package com.ceribit.android.lazynotes;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ceribit.android.lazynotes.database.Note;
import com.ceribit.android.lazynotes.utils.SharedPreferencesManager;

import java.util.List;

public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {

    private List<Note> mNotes;
    private Context mContext;
    private NoteFragment mNoteFragment = null;

    // Set up the adapter to retrieve resources
    public NoteRecyclerViewAdapter(Context context, List<Note> notes) {
        super();
        mContext = context;
        mNotes = notes;
    }


    // Create ViewHolder for the recyclerview items
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View newView = layoutInflater.inflate(R.layout.note_grid_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(newView);

        return viewHolder;
    }


    // Get number of items
    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    // Bind the items to the existing viewholders
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Note currentNote = mNotes.get(position);

        TypedArray colorArray = mContext.getResources().obtainTypedArray(R.array.importance_colors);
        viewHolder.mTitleTextView.setText(currentNote.getTitle());
        viewHolder.mDescriptionTextView.setText(currentNote.getDescription());

        Drawable drawable = mContext.getResources().getDrawable(R.drawable.grid_item_background);
        drawable.setColorFilter(mContext.getResources().getColor
                (colorArray.getResourceId(
                        currentNote.getImportanceLevel(),
                        Note.Importance.NEUTRAL.getImportanceLevel())), PorterDuff.Mode.SRC);

        viewHolder.itemView.setBackground(drawable);
        colorArray.recycle();
    }

    // ViewHolder
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
                    // Get info from the clicked item
                    FragmentManager fragmentManager = ((MainActivity) mContext)
                            .getSupportFragmentManager();
                    mNoteFragment = new NoteFragment();
                    SharedPreferencesManager.setNoteClicked(true);
                    mNoteFragment.setArguments(createNoteBundle(mNotes.get(getAdapterPosition())));

                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.zoom_enter, R.anim.zoom_exit,
                                    R.anim.zoom_enter, R.anim.zoom_exit)
                            .replace(R.id.main_container, mNoteFragment)
                            .addToBackStack(null)
                            .commit();

                }
            });
        }
    }

    // Create bundle
    public static Bundle createNoteBundle(Note note){
        Bundle bundle = new Bundle();
        bundle.putString(NoteFragment.TITLE_KEY, note.getTitle());
        bundle.putString(NoteFragment.DESCRIPTION_KEY, note.getDescription());
        bundle.putInt(NoteFragment.ID_KEY, note.getId());
        bundle.putInt(NoteFragment.IMPORTANCE_KEY, note.getImportanceLevel());

        return bundle;
    }

}
