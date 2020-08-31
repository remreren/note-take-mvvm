package com.bukonudakonusalim.takenotes.utils;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseController extends SQLiteOpenHelper {

    public static final int NOTEBOOKS_DATABASE_VERSION = 1;
    public static final String DB_NAME = "take-notes.db";
    public static final String TABLE_OPTIONS_NAME = "options";
    public static final String TABLE_NOTEBOOKS_NAME = "notebooks";

    public static final String _ID = "id";
    public static final String _CREATED_AT = "created_at";
    public static final String _UPDATED_AT = "updated_at";

    public static final String NOTEBOOKS_NAME = "name";
    public static final String NOTEBOOKS_DESCRIPTION = "description";
    public static final String NOTEBOOKS_COLOR = "color";
    public static final String NOTEBOOKS_DELETED = "is_deleted";
    public static final String NOTEBOOKS_TYPE = "type";

    public static final String NOTES_TITLE = "title";
    public static final String NOTES_CONTENT = "content";
    public static final String NOTES_DELETED = "is_deleted";
    public static final String NOTES_LABELS = "labels";

    public static final String OPTIONS_KEY = "key";
    public static final String OPTIONS_VALUE = "value";

    private static DatabaseController instance = null;

    private SQLiteDatabase db;
    private int openConnections = 0;

    public synchronized static DatabaseController getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseController(context.getApplicationContext());
        }
        return instance;
    }

    public DatabaseController(@Nullable Context context) {
        super(context, DB_NAME, null, NOTEBOOKS_DATABASE_VERSION);
    }

    public void createNotebookTable(long tableId) {
        final SQLiteDatabase db = getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS '" + tableId + "' ('" +
                _ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, '" +
                NOTES_TITLE + "' VARCHAR(250) NOT NULL, '" +
                NOTES_CONTENT + "' VARCHAR(7500) NOT NULL, '" +
                NOTES_LABELS + "' TEXT, '" +
                NOTES_DELETED + "' BOOLEAN NOT NULL DEFAULT 0, '" +
                _CREATED_AT + "' DATETIME DEFAULT CURRENT_TIMESTAMP, '" +
                _UPDATED_AT + "' DATETIME DEFAULT CURRENT_TIMESTAMP);");

        db.execSQL("CREATE TRIGGER '" + tableId + "_trigger" + "' AFTER UPDATE ON '" + tableId +
                "' BEGIN UPDATE '" +
                tableId + "' SET '" +
                _UPDATED_AT + "' = datetime('now') WHERE '" +
                _ID + "' = NEW.'" + _ID + "';" +
                "END;");
    }

    @Override
    public synchronized void close() {
        if (db == null || openConnections == 0) {
            throw new IllegalStateException("No database open");
        }
        openConnections--;
        if (openConnections != 0) {
            return;
        }
        super.close();
    }

    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        if (db == null) {
            db = super.getWritableDatabase();
        }
        openConnections++;
        return db;
    }

    public synchronized SQLiteDatabase database() {
        if (db == null) {
            throw new IllegalStateException("No database opened");
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '" + TABLE_NOTEBOOKS_NAME + "' ('" +
                _ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, '" +
                NOTEBOOKS_NAME + "' VARCHAR(80) NOT NULL, '" +
                NOTEBOOKS_DESCRIPTION + "' VARCHAR(250) NOT NULL, '" +
                NOTEBOOKS_COLOR + "' INTEGER DEFAULT 0xffffff, '" +
                NOTEBOOKS_TYPE + "' INTEGER NOT NULL DEFAULT 1, '" +
                NOTEBOOKS_DELETED + "' BOOLEAN NOT NULL DEFAULT 0, '" +
                _CREATED_AT + "' DATETIME DEFAULT CURRENT_TIMESTAMP, '" +
                _UPDATED_AT + "' DATETIME DEFAULT CURRENT_TIMESTAMP);");

        sqLiteDatabase.execSQL("CREATE TRIGGER tables_trigger AFTER UPDATE ON '" + TABLE_NOTEBOOKS_NAME +
                "' BEGIN UPDATE '" +
                TABLE_NOTEBOOKS_NAME + "' SET '" +
                _UPDATED_AT + "' = datetime('now') WHERE '" +
                _ID + "' = NEW.'" + _ID + "';" +
                "END;");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_OPTIONS_NAME + " ('" +
                OPTIONS_KEY + "' VARCHAR(25) NOT NULL, '" +
                OPTIONS_VALUE + "' VARCHAR(250) NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTEBOOKS_NAME + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_OPTIONS_NAME + ";");
        }
    }

    public long getCount(String table) {
        return DatabaseUtils.queryNumEntries(database(), table);
    }
}
