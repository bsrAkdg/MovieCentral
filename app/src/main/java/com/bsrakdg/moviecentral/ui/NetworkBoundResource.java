package com.bsrakdg.moviecentral.ui;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.bsrakdg.moviecentral.network.responses.ApiResponse;
import com.bsrakdg.moviecentral.utils.Resource;

public abstract class NetworkBoundResource<CacheObject, RequestObject> {

    private MediatorLiveData<Resource<CacheObject>> results = new MediatorLiveData<>();

    public NetworkBoundResource() {
        init();
    }

    private void init() {

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
