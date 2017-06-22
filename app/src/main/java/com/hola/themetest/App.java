package com.hola.themetest;

import android.app.Application;

/**
 * Created by zy on 17-6-22.
 */

public class App extends Application {

    private static App sInstance;

    public static App getApp() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        Config.getInstance().init(this);
    }
}
