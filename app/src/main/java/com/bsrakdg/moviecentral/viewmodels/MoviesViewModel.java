package com.bsrakdg.moviecentral.viewmodels;

import androidx.lifecycle.ViewModel;

import com.bsrakdg.moviecentral.repositories.MoviesRepository;

public class MoviesViewModel extends ViewModel {
    private MoviesRepository moviesRepository;


    public MoviesViewModel() {
        moviesRepository = MoviesRepository.getInstance();
    }
}
