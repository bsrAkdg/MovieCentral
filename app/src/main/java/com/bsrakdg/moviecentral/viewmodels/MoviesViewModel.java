package com.bsrakdg.moviecentral.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bsrakdg.moviecentral.models.Movie;
import com.bsrakdg.moviecentral.repositories.MoviesRepository;
import com.bsrakdg.moviecentral.utils.Resource;

import java.util.List;

public class MoviesViewModel extends ViewModel {
    private MoviesRepository moviesRepository;

    private int genresId;

    public LiveData<Resource<List<Movie>>> getMovies() {
        return moviesRepository.getMovies(genresId);
    }

    public MoviesViewModel() {
        moviesRepository = MoviesRepository.getInstance();
    }

    public void setGenresId(int genresId) {
        this.genresId = genresId;
    }
}
