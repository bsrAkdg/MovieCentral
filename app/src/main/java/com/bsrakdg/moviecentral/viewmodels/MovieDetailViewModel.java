package com.bsrakdg.moviecentral.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bsrakdg.moviecentral.models.Movie;
import com.bsrakdg.moviecentral.repositories.MoviesRepository;
import com.bsrakdg.moviecentral.utils.Resource;

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

    public LiveData<Resource<Movie>> getMovieDetail() {
        return repository.getMovieDetail(moviesId);
    }
}
