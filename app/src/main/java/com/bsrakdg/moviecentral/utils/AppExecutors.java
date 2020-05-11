package com.bsrakdg.moviecentral.utils;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static AppExecutors appExecutors;
    private final Executor background = Executors.newSingleThreadExecutor();
    private final Executor main = new MainThreadExecutor();

    private AppExecutors() {
    }

    public static AppExecutors getInstance() {
        if (appExecutors == null) {
            appExecutors = new AppExecutors();
        }
        return appExecutors;
    }

    public Executor getBackground() {
        return background;
    }

    public Executor getMain() {
        return main;
    }

    private static class MainThreadExecutor implements Executor {

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}
