package com.example.h071211026_finalmobile.database;

import android.database.Cursor;

import com.example.h071211026_finalmobile.model.Film;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Film> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Film> films = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns._ID));
            String backdrop =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.BACKDROP_PATH));
            String poster =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.POSTER_PATH));
            String name =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.NAME));
            String rating =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.RATING));
            String release_date =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.RELEASE_DATE));
            String overview =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FilmColumns.OVERVIEW));

            films.add(new Film(id, backdrop, poster, name, rating, release_date, overview));
        }
        return films;
    }
}