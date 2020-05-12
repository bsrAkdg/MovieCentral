package com.bsrakdg.moviecentral.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.bsrakdg.moviecentral.BaseApplication;
import com.bsrakdg.moviecentral.BuildConfig;
import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.network.MovieApi;
import com.bsrakdg.moviecentral.network.ServiceGenerator;
import com.bsrakdg.moviecentral.network.responses.ApiResponse;
import com.bsrakdg.moviecentral.network.responses.GenresResponse;
import com.bsrakdg.moviecentral.persistence.MovieDao;
import com.bsrakdg.moviecentral.persistence.MovieDatabase;
import com.bsrakdg.moviecentral.ui.NetworkBoundResource;
import com.bsrakdg.moviecentral.utils.AppExecutors;
import com.bsrakdg.moviecentral.utils.Constants;
import com.bsrakdg.moviecentral.utils.Resource;

import java.util.List;

public class GenresRepository {

    private static final String TAG = "GenresRepository";

    private static GenresRepository genresRepository;
    private AppExecutors appExecutors;
    private MovieDao movieDao;
    private MovieApi movieApi;

    private GenresRepository() {
        Log.d(TAG, "GenresRepository: constructor");
        appExecutors = AppExecutors.getInstance();
        movieDao = MovieDatabase.getInstance(BaseApplication.getAppContext()).getMovieDao();
        movieApi = ServiceGenerator.getMovieApi();
    }

    public static GenresRepository getGenresRepositoryInstance() {
        if (genresRepository == null) {
            genresRepository = new GenresRepository();
        }
        return genresRepository;
    }

    public LiveData<Resource<List<Genre>>> queryGenres() {
        return new NetworkBoundResource<List<Genre>, GenresResponse>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull GenresResponse item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List<Genre> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Genre>> loadFromDb() {
                return movieDao.getGenres();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GenresResponse>> createCall() {
                return movieApi.getGenres(BuildConfig.API_KEY, Constants.LANGUAGE);
            }
        }.getAsLiveData();
    }
}
