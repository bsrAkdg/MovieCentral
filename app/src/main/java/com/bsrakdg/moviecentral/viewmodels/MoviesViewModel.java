package com.bsrakdg.moviecentral.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bsrakdg.moviecentral.adapters.MoviesAdapter;
import com.bsrakdg.moviecentral.models.Movie;
import com.bsrakdg.moviecentral.repositories.MoviesRepository;
import com.bsrakdg.moviecentral.utils.Resource;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private MoviesRepository moviesRepository;

    private MoviesAdapter moviesAdapter;

    public MoviesViewModel() {
        moviesRepository = MoviesRepository.getInstance();
        moviesAdapter = new MoviesAdapter();
    }
    private int genresId;

    public LiveData<Resource<List<Movie>>> getMovies() {
        return moviesRepository.getMovies(genresId);
    }

    public MoviesAdapter getMoviesAdapter() {
        return moviesAdapter;
    }

    public void setGenresId(int genresId) {
        this.genresId = genresId;
    }
}
