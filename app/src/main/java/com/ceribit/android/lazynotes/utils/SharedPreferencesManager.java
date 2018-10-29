package com.ceribit.android.lazynotes.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ceribit.android.lazynotes.database.Note;

public class SharedPreferencesManager {
    private SharedPreferencesManager() {}

    private static SharedPreferences mPref;
    private static SharedPreferences.Editor mEditor;

    private static String NOTE_TITLE = "note_title";
    private static String NOTE_DESCRIPTION = "note_description";
    private static String NOTE_ID = "note_id";
    private static String NOTE_IS_CLICKED = "note_is_clicked";
    private static String NOTE_IMPORTANCE = "note_importance";

    public static void init(Context context){
        mPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public static SharedPreferences getInstance(){
        return mPref;
    }

    public static void storeNote(Note note){
        if(mEditor != null){
            mEditor.putInt(NOTE_ID, note.getId());
            mEditor.putString(NOTE_TITLE, note.getTitle());
            mEditor.putString(NOTE_DESCRIPTION, note.getDescription());
            mEditor.putInt(NOTE_IMPORTANCE, note.getImportanceLevel());
            mEditor.apply();
        }
    }

    @Nullable
    public static Note getNote(){
        if(mPref != null){
            int id =  mPref.getInt(NOTE_ID, -1);
            String title = mPref.getString(NOTE_TITLE, "");
            String description = mPref.getString(NOTE_DESCRIPTION, "");
            Note note = new Note(title, description, mPref.getInt(NOTE_IMPORTANCE, 1));
            if(id != -1){
                note.setId(id);
            }
            return note;
        }
        return null;
    }

    public static void setNoteClicked(boolean noteClicked){
        mEditor.putBoolean(NOTE_IS_CLICKED, noteClicked);
        mEditor.apply();
    }

    public static boolean isNoteClicked(){
        return mPref.getBoolean(NOTE_IS_CLICKED, false);
    }
}
