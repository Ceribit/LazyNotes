package com.ceribit.android.lazynotes.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ceribit.android.lazynotes.Database.NoteContract.NoteEntry;
public class NoteDbHelper extends SQLiteOpenHelper {

    // Database info
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Notes.db";

    public NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public NoteDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_PETS_TABLE =
                "CREATE TABLE NOTES("
                + NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NoteEntry.COLUMN_NOTE_TITLE + " TEXT NOT NULL,"
                + NoteEntry.COLUMN_NOTE_DESCRIPTION + " TEXT NOT NULL,"
                + NoteEntry.COLUMN_NOTE_IMPORTANCE + " INTEGER NOT NULL DEFAULT 1)；";
        sqLiteDatabase.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        final String SQL_DROP_TABLE = "DROP TABLE NOTES";
        sqLiteDatabase.execSQL(SQL_DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
