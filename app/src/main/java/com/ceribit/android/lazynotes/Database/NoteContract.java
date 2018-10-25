package com.ceribit.android.lazynotes.Database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class NoteContract {

    /** Content Authority*/
    public static final String CONTENT_AUTHORITY = "com.ceribit.android.lazynotes";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_NOTES = "lazynotes";

    public static abstract class NoteEntry implements BaseColumns{
        /**
            The MIME type of the Content Authority for a list of notes
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NOTES;

        /**
         *  The MIME type for a single pet
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NOTES;

        /** Content Uri for the Notes Table */
        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(BASE_CONTENT_URI, PATH_NOTES);

        public static final String TABLE_NAME = "Notes";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NOTE_TITLE = "title";
        public static final String COLUMN_NOTE_DESCRIPTION = "description";
        public static final String COLUMN_NOTE_IMPORTANCE = "importance";
        public static final String COLUMN_NOTE_BACKGROUND_COLOR = "color";

        /**
         * Possible values for a note's importance
         */
        public static final int IMPORTANCE_NOT_IMPORTANT = 0;
        public static final int IMPORTANCE_NEUTRAL = 1;
        public static final int IMPORTANCE_IMPORTANT = 2;
        public static final int IMPORTANCE_VERY_IMPORTANT = 3;

        public static boolean isValidImportance(int importance){
            return( importance == IMPORTANCE_NOT_IMPORTANT ||
                    importance == IMPORTANCE_NEUTRAL ||
                    importance == IMPORTANCE_IMPORTANT ||
                    importance == IMPORTANCE_VERY_IMPORTANT );
        }
    }
}
