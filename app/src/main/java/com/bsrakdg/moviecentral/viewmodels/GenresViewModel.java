package com.bsrakdg.moviecentral.viewmodels;

import androidx.lifecycle.ViewModel;

import com.bsrakdg.moviecentral.repositories.GenresRepository;

public class GenresViewModel extends ViewModel {

    private static final String TAG = "GenresViewModel";

    private GenresRepository genresRepository;

    public GenresViewModel() {
        genresRepository = GenresRepository.getGenresRepositoryInstance();
    }

}
