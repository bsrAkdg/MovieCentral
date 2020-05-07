package com.bsrakdg.moviecentral.network;

import com.bsrakdg.moviecentral.network.responses.GenresResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("3/genre/movie/list")
    Call<GenresResponse> getGenres(@Query("api_key") String apiKey,
                                   @Query("language") String language);
}
