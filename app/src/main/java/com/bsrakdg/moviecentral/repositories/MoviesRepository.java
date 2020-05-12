package com.bsrakdg.moviecentral.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.bsrakdg.moviecentral.BuildConfig;
import com.bsrakdg.moviecentral.models.Movie;
import com.bsrakdg.moviecentral.network.responses.ApiResponse;
import com.bsrakdg.moviecentral.network.responses.MoviesResponse;
import com.bsrakdg.moviecentral.ui.NetworkBoundResource;
import com.bsrakdg.moviecentral.utils.Constants;
import com.bsrakdg.moviecentral.utils.Resource;

import java.util.List;

public class MoviesRepository extends BaseRepository {
    private static final String TAG = "MoviesRepository";

    private static MoviesRepository moviesRepository;
    private int page = 1;

    private MoviesRepository() {
        super();
        Log.d(TAG, "MoviesRepository: create");
    }

    public static MoviesRepository getInstance() {
        if (moviesRepository == null) {
            moviesRepository = new MoviesRepository();
        }
        return moviesRepository;
    }

    public LiveData<Resource<List<Movie>>> getMovies(int genreId) {
        return new NetworkBoundResource<List<Movie>, MoviesResponse>(getAppExecutors()) {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {

            }

            @Override
            protected boolean shouldFetch(@Nullable List<Movie> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MoviesResponse>> createCall() {
                return getMovieApi()
                        .getMovies(BuildConfig.API_KEY, Constants.LANGUAGE, Constants.POPULARITY_DESC, page, genreId);
            }
        }.getAsLiveData();
    }
}
