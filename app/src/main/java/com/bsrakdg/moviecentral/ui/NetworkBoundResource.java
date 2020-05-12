package com.bsrakdg.moviecentral.ui;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.bsrakdg.moviecentral.network.responses.ApiResponse;
import com.bsrakdg.moviecentral.utils.AppExecutors;
import com.bsrakdg.moviecentral.utils.Constants;
import com.bsrakdg.moviecentral.utils.Resource;

public abstract class NetworkBoundResource<CacheObject, RequestObject> {

    private MediatorLiveData<Resource<CacheObject>> results = new MediatorLiveData<>();
    private AppExecutors appExecutors;

    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        init();
    }

    private void fetchFromNetwork(LiveData<CacheObject> dbSource) {
        // loading
        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        results.addSource(dbSource, cacheObject -> results.setValue(Resource.loading()));

        // request at
        LiveData<ApiResponse<RequestObject>> apiResponseLiveData = createCall();
        results.addSource(apiResponseLiveData, response -> {
            results.removeSource(apiResponseLiveData);
            results.removeSource(dbSource);

            if (response != null) {
                if (response instanceof ApiResponse.ApiSuccessResponse) {
                    appExecutors.getBackground().execute(() -> {
                        // db ye kaydet
                        saveCallResult(handleResponse((ApiResponse.ApiSuccessResponse) response));

                        results.addSource(loadFromDb(), cacheObject -> results
                                .setValue(Resource.success(cacheObject)));
                    });
                } else if (response instanceof ApiResponse.ApiErrorResponse) {
                    results.setValue(Resource
                            .error(((ApiResponse.ApiErrorResponse) response).getMessage()));
                } else {
                    results.setValue(Resource.error(Constants.EMPTY));
                }
            }
        });

    }

    private RequestObject handleResponse(ApiResponse.ApiSuccessResponse response) {
        return (RequestObject) response.getBody();
    }

    private void init() {
        // loading
        results.setValue(Resource.loading());

        // loadFromDb
        LiveData<CacheObject> dbSource = loadFromDb();
        results.addSource(dbSource, cacheObject -> {
            results.removeSource(dbSource);
            if (shouldFetch(cacheObject)) {
                fetchFromNetwork(dbSource);
            } else {
                results.addSource(dbSource, cacheObject1 -> results
                        .setValue(Resource.success(cacheObject1)));
            }
        });
    }

    // Returns a LiveData object that represents the resource that's implemented
    // in the base class.
    public final LiveData<Resource<CacheObject>> getAsLiveData() {
        return results;
    }

    // Called to save the result of the API response into the database.
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestObject item);

    // Called with the data in the database to decide whether to fetch
    // potentially updated data from the network.
    @MainThread
    protected abstract boolean shouldFetch(@Nullable CacheObject data);

    // Called to get the cached data from the database.
    @NonNull
    @MainThread
    protected abstract LiveData<CacheObject> loadFromDb();

    // Called to create the API call.
    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestObject>> createCall();
}
