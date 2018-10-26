package com.ceribit.android.lazynotes.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class NoteProvider extends ContentProvider {
    /**
     * Global Variables
     * */
    NoteDbHelper mDbHelper;
    SQLiteDatabase mDatabase;

    /** Uri Matcher Codes */
    private static final int NOTES = 101;
    private static final int NOTE_ID = 102;

    /** UriMatcher to match content URIs to their corresponding code */
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Static Initializer. When this class is first called, this instantiates the static UriMatcher
    static {
        sUriMatcher.addURI(NoteContract.CONTENT_AUTHORITY, NoteContract.PATH_NOTES, NOTES);
        sUriMatcher.addURI(NoteContract.CONTENT_AUTHORITY, NoteContract.PATH_NOTES + "/#", NOTE_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new NoteDbHelper(getContext());
        mDatabase = mDbHelper.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // Get readable database
        mDatabase = mDbHelper.getReadableDatabase();
        Cursor cursor = null;
        int match = sUriMatcher.match(uri);
        switch (match){
            case NOTES:
                cursor = mDatabase.query(NoteContract.NoteEntry.TABLE_NAME,
                        null, null, null, null, null
                ,null);
            case NOTE_ID:
                break;
        }

        if(cursor != null){
            //cursor.close();
        }

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        if(contentValues == null){
            return null;
        }

        switch(sUriMatcher.match(uri)){
            case NOTES:
                String noteTitle = contentValues.getAsString(NoteContract.NoteEntry.COLUMN_NOTE_TITLE);
                if(isValidText(noteTitle)) {
                    long _id = db.insert(NoteContract.NoteEntry.TABLE_NAME, null, contentValues);
                    return ContentUris.withAppendedId(uri, _id);
                }

        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch(match){
            case NOTES:
                break;
            case NOTE_ID:
                SQLiteDatabase database = mDbHelper.getWritableDatabase();
                selection = NoteContract.NoteEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};
                int rowsDeleted =  database.delete(
                        NoteContract.NoteEntry.TABLE_NAME, selection, selectionArgs);
                return rowsDeleted;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues,
                      @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        int rowsUpdated = -1;
        switch(match){
            case NOTES:
                break;
            case NOTE_ID:
                SQLiteDatabase database = mDbHelper.getWritableDatabase();
                selection = NoteContract.NoteEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri))};
                String title = contentValues.getAsString(NoteContract.NoteEntry.COLUMN_NOTE_TITLE);
                if(isValidText(title)) {
                    rowsUpdated = database.update(NoteContract.NoteEntry.TABLE_NAME, contentValues, selection, selectionArgs);
                    Log.e("Update", "Rows updated : " + rowsUpdated);
                }

        }
        Log.e("Update", "Rows updated : " + rowsUpdated);

        return rowsUpdated;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case NOTES:
                return NoteContract.NoteEntry.CONTENT_LIST_TYPE;
            case NOTE_ID:
                return NoteContract.NoteEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    public static boolean isValidText(String word){
        boolean isOnlyWhiteSpace = (word.trim().length() == 0);
        return !(word.isEmpty() || isOnlyWhiteSpace);
    }
}
