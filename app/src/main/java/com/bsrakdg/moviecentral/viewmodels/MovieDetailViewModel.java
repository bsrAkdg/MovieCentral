package com.bsrakdg.moviecentral.viewmodels;

import androidx.lifecycle.ViewModel;

import com.bsrakdg.moviecentral.repositories.MoviesRepository;

public class MovieDetailViewModel extends ViewModel {
    private int moviesId;
    private MoviesRepository repository;

    public MovieDetailViewModel() {
        repository = MoviesRepository.getInstance();
    }

    public int getMoviesId() {
        return moviesId;
    }

    public void setMoviesId(int moviesId) {
        this.moviesId = moviesId;
    }

}
