package com.bsrakdg.moviecentral.network;

import androidx.lifecycle.LiveData;

import com.bsrakdg.moviecentral.network.responses.ApiResponse;
import com.bsrakdg.moviecentral.network.responses.GenresResponse;
import com.bsrakdg.moviecentral.network.responses.MoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("3/genre/movie/list")
    LiveData<ApiResponse<GenresResponse>> getGenres(@Query("api_key") String apiKey,
                                                    @Query("language") String language);

    @GET("3/discover/movie")
    LiveData<ApiResponse<MoviesResponse>> getMovies(@Query("api_key") String apiKey,
                                                    @Query("language") String language,
                                                    @Query("sort_by") String sort_by,
                                                    @Query("page") int page,
                                                    @Query("with_genres") int genre);

}
