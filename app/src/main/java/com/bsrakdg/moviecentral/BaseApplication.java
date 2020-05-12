package com.bsrakdg.moviecentral;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }
}
