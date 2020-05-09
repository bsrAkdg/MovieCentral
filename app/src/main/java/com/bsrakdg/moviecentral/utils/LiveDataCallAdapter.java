package com.bsrakdg.moviecentral.utils;

import androidx.lifecycle.LiveData;

import com.bsrakdg.moviecentral.network.responses.ApiResponse;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * R is default retrofit response type
 * Second generic class is expected type
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<ApiResponse<R>>> {

    private Type type;

    /**
     * Type comes from
     */
    public LiveDataCallAdapter(Type type) {
        this.type = type;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public LiveData<ApiResponse<R>> adapt(final Call<R> call) {
        return new LiveData<ApiResponse<R>>() {
            /** you should use onActive func when LiveData modifying*/
            @Override
            protected void onActive() {
                super.onActive();
                /* We need to convert the R value in Call<R> that comes to us
                  to the LiveData<ApiResponse> value.
                  We should call request and modify response with create methods on ApiResponse.
                  Then we need to post the values ​​we obtained(LiveData<ApiResponse>>) to LiveData.
                 */
                final ApiResponse apiResponse = new ApiResponse();

                // Call request
                call.enqueue(new Callback<R>() {
                    @Override
                    public void onResponse(Call<R> call, Response<R> response) {
                        // Modify request
                        ApiResponse<R> obtainedResponse = apiResponse.create(response);
                        // Post new LiveData value
                        postValue(obtainedResponse);
                    }

                    @Override
                    public void onFailure(Call<R> call, Throwable t) {
                        //Modify request
                        ApiResponse<R> obtainedResponse = apiResponse.create(t);
                        postValue(obtainedResponse);
                    }
                });
            }
        };
    }
}
