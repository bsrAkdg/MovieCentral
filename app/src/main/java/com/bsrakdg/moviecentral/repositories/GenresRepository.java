package com.bsrakdg.moviecentral.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.bsrakdg.moviecentral.models.Genre;

import java.util.List;

public class GenresRepository {

    private static final String TAG = "GenresRepository";

    private static GenresRepository genresRepository;

    private GenresRepository() {
        Log.d(TAG, "GenresRepository: constructor");
    }

    public static GenresRepository getGenresRepositoryInstance() {
        if (genresRepository == null) {
            genresRepository = new GenresRepository();
        }
        return genresRepository;
    }

    public LiveData<List<Genre>> getGenres() {
        Log.d(TAG, "getGenres: ");
        return null;
    }
}
