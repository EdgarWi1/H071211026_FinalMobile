package com.example.h071211026_finalmobile.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "NontonkahINI";

    public static final class FilmColumns implements BaseColumns {

        public static String BACKDROP_PATH = "backdropPath";
        public static String POSTER_PATH = "posterPath";
        public static String NAME = "name";
        public static String RATING = "rating";
        public static String RELEASE_DATE = "releaseDate";
        public static String OVERVIEW = "overview";
    }
}

