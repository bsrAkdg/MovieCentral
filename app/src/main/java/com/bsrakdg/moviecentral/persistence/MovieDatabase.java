package com.bsrakdg.moviecentral.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.bsrakdg.moviecentral.models.Genre;

@Database(entities = {Genre.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MovieDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "MovieCentral";
    private static MovieDatabase database;

    public static MovieDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, MovieDatabase.class, DATABASE_NAME).build();
        }
        return database;
    }

    public abstract MovieDao getMovieDao();
}
