package com.bsrakdg.moviecentral.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.bsrakdg.moviecentral.BuildConfig;
import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.network.ServiceGenerator;
import com.bsrakdg.moviecentral.network.responses.ApiResponse;
import com.bsrakdg.moviecentral.network.responses.GenresResponse;
import com.bsrakdg.moviecentral.utils.Constants;

import java.util.List;

public class GenresRepository {

    private static final String TAG = "GenresRepository";

    private static GenresRepository genresRepository;
    private MediatorLiveData<List<Genre>> mediatorGenres = new MediatorLiveData<>();

    private GenresRepository() {
        Log.d(TAG, "GenresRepository: constructor");
    }

    public static GenresRepository getGenresRepositoryInstance() {
        if (genresRepository == null) {
            genresRepository = new GenresRepository();
        }
        return genresRepository;
    }

    public LiveData<List<Genre>> getGenres() {
        return mediatorGenres;
    }

    public void queryGenres() {
        final LiveData<ApiResponse<GenresResponse>> queryGenres
                = ServiceGenerator.getRetrofit().getGenres(BuildConfig.API_KEY, Constants.LANGUAGE);

        mediatorGenres.addSource(queryGenres, new Observer<ApiResponse<GenresResponse>>() {
            @Override
            public void onChanged(ApiResponse<GenresResponse> response) {
                mediatorGenres.removeSource(queryGenres);
                if (response instanceof ApiResponse.ApiSuccessResponse) {
                    GenresResponse genresResponse =
                            (GenresResponse) ((ApiResponse.ApiSuccessResponse) response)
                            .getBody();
                    mediatorGenres.setValue(genresResponse.getGenres());
                }
            }
        });

    }
}
