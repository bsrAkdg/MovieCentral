package com.bsrakdg.moviecentral.repositories;

import android.text.TextUtils;
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
                if (item.getResults() != null) {
                    int index = 0;
                    for (long id : getMovieDao().saveMovies(item.getResults())) {
                        if (id == -1) {
                            // conflict, update old data
                            Log.d(TAG, "saveCallResult: getMovies conflict");
                            Movie movie = item.getResults().get(index);
                            getMovieDao().updateMovie(
                                    movie.getId(),
                                    movie.getPopularity(),
                                    movie.getVote_count(),
                                    movie.getVote_average(),
                                    movie.getTitle(),
                                    movie.getRelease_date(),
                                    movie.getPoster_path(),
                                    movie.getOverview());
                        }
                        index++;
                    }
                    Log.d(TAG, "saveCallResult: getMovies complete");
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Movie> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return getMovieDao().getMovies();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MoviesResponse>> createCall() {
                return getMovieApi()
                        .getMovies(BuildConfig.API_KEY, Constants.LANGUAGE,
                                Constants.POPULARITY_DESC, page, genreId);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<Movie>> getMovieDetail(int movieId) {
        return new NetworkBoundResource<Movie, Movie>(getAppExecutors()) {
            @Override
            protected void saveCallResult(@NonNull Movie movie) {
                long id = getMovieDao().saveMovie(movie);
                if (-1 == id) {
                    getMovieDao().updateMovie(
                            movie.getId(),
                            movie.getPopularity(),
                            movie.getVote_count(),
                            movie.getVote_average(),
                            movie.getTitle(),
                            movie.getRelease_date(),
                            movie.getPoster_path(),
                            movie.getOverview());
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Movie data) {
                return data == null || TextUtils.isEmpty(data.getTitle());
            }

            @NonNull
            @Override
            protected LiveData<Movie> loadFromDb() {
                return getMovieDao().getMovieDetail(movieId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Movie>> createCall() {
                return getMovieApi().getMovieDetail(movieId, BuildConfig.API_KEY, Constants.LANGUAGE);
            }
        }.getAsLiveData();
    }
}
