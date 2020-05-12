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
                if (item.getGenres() != null) {
                    int index = 0;
                    for (long id : movieDao.saveGenres(item.getGenres())) {
                        if (id == -1) {
                            // conflict, update old data
                            Log.d(TAG, "saveCallResult: queryGenres conflict");
                            Genre genre = item.getGenres().get(index);
                            movieDao.updateGenre(genre.getId(), genre.getName(),
                                    (int) System.currentTimeMillis() / 1000);
                        }
                        index++;
                    }
                    Log.d(TAG, "saveCallResult: queryGenres complete");
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Genre> data) {
                return true; //data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<Genre>> loadFromDb() {
                Log.d(TAG, "loadFromDb: queryGenres");
                return movieDao.getGenres();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GenresResponse>> createCall() {
                Log.d(TAG, "createCall: queryGenres");
                return movieApi.getGenres(BuildConfig.API_KEY, Constants.LANGUAGE);
            }
        }.getAsLiveData();
    }
}
