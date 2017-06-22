package com.hola.themetest;

import android.content.Context;

/**
 * Created by zy on 17-6-22.
 */

public class Config {
    private static Config sInstance;

    private Config() {}

    public static Config getInstance() {
        if (sInstance == null) {
            sInstance = new Config();
        }
        return sInstance;
    }

    public float density;

    public void init(Context context) {
        density = context.getResources().getDisplayMetrics().density;
    }

    public static int dp2px(float dp) {
        return Math.round(dp * Config.getInstance().density);
    }
}
