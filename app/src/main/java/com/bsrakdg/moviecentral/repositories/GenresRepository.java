package com.bsrakdg.moviecentral.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.network.responses.ApiResponse;
import com.bsrakdg.moviecentral.network.responses.GenresResponse;
import com.bsrakdg.moviecentral.ui.NetworkBoundResource;
import com.bsrakdg.moviecentral.utils.AppExecutors;
import com.bsrakdg.moviecentral.utils.Resource;

import java.util.List;

public class GenresRepository {

    private static final String TAG = "GenresRepository";

    private static GenresRepository genresRepository;
    private AppExecutors appExecutors;

    private GenresRepository() {
        Log.d(TAG, "GenresRepository: constructor");
        appExecutors = AppExecutors.getInstance();
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
                return false;
            }

            @NonNull
            @Override
            protected LiveData<List<Genre>> loadFromDb() {
                return null;
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GenresResponse>> createCall() {
                return null;
            }
        }.getAsLiveData();
    }
}
