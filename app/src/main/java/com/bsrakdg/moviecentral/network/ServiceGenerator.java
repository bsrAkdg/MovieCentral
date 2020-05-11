package com.bsrakdg.moviecentral.network;

import com.bsrakdg.moviecentral.utils.Constants;
import com.bsrakdg.moviecentral.utils.LiveDataCallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit retrofit;

    private ServiceGenerator() {
    }

    public static MovieApi getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MovieApi.class);
    }

}
