package com.bsrakdg.moviecentral.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.bsrakdg.moviecentral.BuildConfig;
import com.bsrakdg.moviecentral.models.Genre;
import com.bsrakdg.moviecentral.network.responses.ApiResponse;
import com.bsrakdg.moviecentral.network.responses.GenresResponse;
import com.bsrakdg.moviecentral.ui.NetworkBoundResource;
import com.bsrakdg.moviecentral.utils.Constants;
import com.bsrakdg.moviecentral.utils.Resource;

import java.util.List;

public class GenresRepository extends BaseRepository {

    private static final String TAG = "GenresRepository";

    private static GenresRepository genresRepository;

    private GenresRepository() {
        super();
        Log.d(TAG, "GenresRepository: create");
    }

    public static GenresRepository getGenresRepositoryInstance() {
        if (genresRepository == null) {
            genresRepository = new GenresRepository();
        }
        return genresRepository;
    }

    public LiveData<Resource<List<Genre>>> queryGenres() {
        return new NetworkBoundResource<List<Genre>, GenresResponse>(getAppExecutors()) {
            @Override
            protected void saveCallResult(@NonNull GenresResponse item) {
                if (item.getGenres() != null) {
                    int index = 0;
                    for (long id : getMovieDao().saveGenres(item.getGenres())) {
                        if (id == -1) {
                            // conflict, update old data
                            Log.d(TAG, "saveCallResult: queryGenres conflict");
                            Genre genre = item.getGenres().get(index);
                            getMovieDao().updateGenre(genre.getId(), genre.getName(),
                                    (int) System.currentTimeMillis() / 1000);
                        }
                        index++;
                    }
                    Log.d(TAG, "saveCallResult: queryGenres complete");
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Genre> data) {
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<Genre>> loadFromDb() {
                Log.d(TAG, "loadFromDb: queryGenres");
                return getMovieDao().getGenres();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GenresResponse>> createCall() {
                Log.d(TAG, "createCall: queryGenres");
                return getMovieApi().getGenres(BuildConfig.API_KEY, Constants.LANGUAGE);
            }
        }.getAsLiveData();
    }
}
