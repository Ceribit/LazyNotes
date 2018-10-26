package com.ceribit.android.lazynotes.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.ceribit.android.lazynotes.database.NoteContract.NoteEntry;

import java.util.ArrayList;
import java.util.List;

public class NotePresenter {


    public static void addNote(Context context, Note note){
        ContentResolver contentResolver = context.getContentResolver();

        ContentValues contentValues = new ContentValues();

        contentValues.put(NoteEntry.COLUMN_NOTE_TITLE, note.getTitle());
        contentValues.put(NoteEntry.COLUMN_NOTE_DESCRIPTION, note.getDescription());
        contentValues.put(NoteEntry.COLUMN_NOTE_IMPORTANCE, note.getImportanceLevel());


        contentResolver.insert(NoteEntry.CONTENT_URI, contentValues);
    }

    public static void updateNote(Context context, Note note){

    }

    public static ArrayList<Note> getAllNotes(Context context){
        ContentResolver contentResolver = context.getContentResolver();

        Cursor cursor = contentResolver.query(NoteEntry.CONTENT_URI,
                null,
                null,
                null,
                null);

        int ID_INDEX = cursor.getColumnIndex(NoteEntry._ID);
        int TITLE_INDEX = cursor.getColumnIndex(NoteEntry.COLUMN_NOTE_TITLE);
        int DESCRIPTION_INDEX = cursor.getColumnIndex(NoteEntry.COLUMN_NOTE_DESCRIPTION);
        int IMPORTANCE_INDEX = cursor.getColumnIndex(NoteEntry.COLUMN_NOTE_IMPORTANCE);

        ArrayList<Note> noteList = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Note newNote = new Note(
                    cursor.getString(TITLE_INDEX),
                    cursor.getString(DESCRIPTION_INDEX),
                    Integer.valueOf(cursor.getString(IMPORTANCE_INDEX))
            );
            newNote.setId(cursor.getInt(ID_INDEX));
            cursor.moveToNext();
            noteList.add(newNote);
        }
        cursor.close();

        return noteList;
    }
}
