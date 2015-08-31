package org.sopin.myenglish;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class WordsDBOpenHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyEnglish.db";

    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_TRANSLATE = "translate";
        public static final String COLUMN_NAME_CONTEXT = "context";
        public static final String COLUMN_NAME_IS_PHRASE = "is_phrase";
        public static final String COLUMN_NAME_LEVEL = "level";
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.COLUMN_NAME_WORD + " VARCHAR(64)," +
                    FeedEntry.COLUMN_NAME_TRANSLATE + " VARCHAR(64)," +
                    FeedEntry.COLUMN_NAME_CONTEXT + " TEXT," +
                    FeedEntry.COLUMN_NAME_IS_PHRASE + " INTEGER," +
                    FeedEntry.COLUMN_NAME_LEVEL + " INTEGER" +
            " );";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public WordsDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}