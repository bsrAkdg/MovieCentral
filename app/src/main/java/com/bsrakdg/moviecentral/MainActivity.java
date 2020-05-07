package com.bsrakdg.moviecentral;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bsrakdg.moviecentral.network.MovieApi;
import com.bsrakdg.moviecentral.network.ServiceGenerator;
import com.bsrakdg.moviecentral.network.responses.GenresResponse;
import com.bsrakdg.moviecentral.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieApi movieApi = ServiceGenerator.getRetrofit().create(MovieApi.class);
        Call<GenresResponse> callGenres = movieApi
                .getGenres(BuildConfig.API_KEY, Constants.LANGUAGE);
        callGenres.enqueue(new Callback<GenresResponse>() {
            @Override
            public void onResponse(Call<GenresResponse> call, Response<GenresResponse> response) {
                Log.d(TAG, "onResponse: response : " + response.body().getGenres());
            }

            @Override
            public void onFailure(Call<GenresResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
