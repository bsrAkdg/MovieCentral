package com.bsrakdg.moviecentral.repositories;

import com.bsrakdg.moviecentral.BaseApplication;
import com.bsrakdg.moviecentral.network.MovieApi;
import com.bsrakdg.moviecentral.network.ServiceGenerator;
import com.bsrakdg.moviecentral.persistence.MovieDao;
import com.bsrakdg.moviecentral.persistence.MovieDatabase;
import com.bsrakdg.moviecentral.utils.AppExecutors;

class BaseRepository {
    private AppExecutors appExecutors;
    private MovieDao movieDao;
    private MovieApi movieApi;

    BaseRepository() {
        appExecutors = AppExecutors.getInstance();
        movieDao = MovieDatabase.getInstance(BaseApplication.getAppContext()).getMovieDao();
        movieApi = ServiceGenerator.getMovieApi();
    }

    AppExecutors getAppExecutors() {
        return appExecutors;
    }

    MovieApi getMovieApi() {
        return movieApi;
    }

    MovieDao getMovieDao() {
        return movieDao;
    }

}
