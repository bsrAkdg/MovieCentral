package com.bsrakdg.moviecentral.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bsrakdg.moviecentral.adapters.GenresAdapter;
import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.repositories.GenresRepository;
import com.bsrakdg.moviecentral.utils.Resource;

import java.util.List;

public class GenresViewModel extends ViewModel {

    private static final String TAG = "GenresViewModel";

    private GenresRepository genresRepository;
    private GenresAdapter adapter;

    public GenresViewModel() {
        Log.d(TAG, "GenresViewModel: constructor");
        genresRepository = GenresRepository.getGenresRepositoryInstance();
        adapter = new GenresAdapter();
    }

    public GenresAdapter getAdapter() {
        return adapter;
    }

    public LiveData<Resource<List<Genre>>> getGenres() {
        return genresRepository.queryGenres();
    }
}
