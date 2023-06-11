package com.example.h071211026_finalmobile.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FilmHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile FilmHelper INSTANCE;
    public FilmHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }
    public static FilmHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FilmHelper(context);
                }
            }
        }
        return INSTANCE;
    }
    public static void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public static SQLiteDatabase openRead() throws SQLException {
        database = databaseHelper.getReadableDatabase();
        return database;
    }

    public static void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }
    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.FilmColumns._ID + " ASC"
        );
    }
    public Cursor queryByTItle(String title) {
        return database.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.FilmColumns.NAME + " = ?",
                new String[]{title},
                null,
                null,
                null,
                null
        );
    }
    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, DatabaseContract.FilmColumns._ID
                + " = ?", new String[]{id});
    }
    public void deleteByTitle(String title) {
        database.delete(DATABASE_TABLE, DatabaseContract.FilmColumns.NAME + " = "
                + title, null);
    }
}