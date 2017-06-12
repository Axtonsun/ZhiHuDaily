package com.example.axtonsun.zhihudaily;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.example.axtonsun.zhihudaily.Utility.SharedPreferencesUtils;

/**
 * Created by Axton on 2017/6/11.
 */

public class AppContext extends Application {
    private static Context context;
    private SharedPreferencesUtils utils;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        utils = new SharedPreferencesUtils(context);
        boolean isNightMode = utils.isNight();
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    public static Context getContextObject(){
        return context;
    }
}
