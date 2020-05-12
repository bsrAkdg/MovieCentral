package com.bsrakdg.moviecentral.repositories;

import android.util.Log;

import com.bsrakdg.moviecentral.BaseApplication;
import com.bsrakdg.moviecentral.models.Movie;
import com.bsrakdg.moviecentral.network.MovieApi;
import com.bsrakdg.moviecentral.network.ServiceGenerator;
import com.bsrakdg.moviecentral.persistence.MovieDao;
import com.bsrakdg.moviecentral.persistence.MovieDatabase;
import com.bsrakdg.moviecentral.utils.AppExecutors;

public class MoviesRepository {
    private static final String TAG = "MoviesRepository";

    private static MoviesRepository moviesRepository;
    private AppExecutors appExecutors;
    private MovieDao movieDao;
    private MovieApi movieApi;

    private MoviesRepository() {
        Log.d(TAG, "MoviesRepository: ");
        appExecutors = AppExecutors.getInstance();
        movieDao = MovieDatabase.getInstance(BaseApplication.getAppContext()).getMovieDao();
        movieApi = ServiceGenerator.getMovieApi();
    }

    public static MoviesRepository getInstance() {
        if (moviesRepository == null) {
            moviesRepository = new MoviesRepository();
        }
        return moviesRepository;
    }
}
