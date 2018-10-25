package com.ceribit.android.lazynotes.Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.ceribit.android.lazynotes.Database.NoteContract.NoteEntry;

import java.util.ArrayList;
import java.util.List;

public class NotePresenter {

    public static void addNote(Context context, Note note){
        ContentResolver contentResolver = context.getContentResolver();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NoteEntry.COLUMN_NOTE_TITLE, note.getTitle());
        contentValues.put(NoteEntry.COLUMN_NOTE_DESCRIPTION, note.getDescription());
        contentValues.put(NoteEntry.COLUMN_NOTE_IMPORTANCE, note.getImportanceLevel());

        Uri noteUri = NoteEntry.CONTENT_URI;

        contentResolver.insert(noteUri, contentValues);
    }

    public static List<Note> getAllNotes(Context context){
        ContentResolver contentResolver = context.getContentResolver();


        Uri queryAllNotesUri = Uri.parse(NoteEntry.CONTENT_URI + "/101");

        Cursor cursor = contentResolver.query(queryAllNotesUri,
                null,
                null,
                null,
                null);

        ArrayList<Note> noteList = new ArrayList<>();
        if(cursor != null) {
            cursor.moveToPosition(0);
            while (cursor.moveToNext()){
                Log.e("NotePresenter", "Note Presenter Test" + cursor.getString(0));
            }
        }
        return noteList;
    }
}
