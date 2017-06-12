package com.example.axtonsun.zhihudaily.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.axtonsun.zhihudaily.Bean.DayNight;

/**
 * Created by Axton on 2017/6/11.
 */

public class SharedPreferencesUtils {
    public static final String FILE_NAME = "sharePreferences_data";

    private final static String MODE = "day_night_mode";

    private SharedPreferences mSharedPreferences;

    public SharedPreferencesUtils(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 保存模式设置
     *
     * @param mode
     * @return
     */
    public boolean setMode(DayNight mode) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(MODE, mode.getName());
        return editor.commit();
    }

    /**
     * 夜间模式
     *
     * @return
     */
    public boolean isNight() {
        String mode = mSharedPreferences.getString(MODE, DayNight.NIGHT.getName());
        if (DayNight.NIGHT.getName().equals(mode)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 日间模式
     *
     * @return
     */
    public boolean isDay() {
        String mode = mSharedPreferences.getString(MODE, DayNight.DAY.getName());
        if (DayNight.DAY.getName().equals(mode)) {
            return true;
        } else {
            return false;
        }
    }
}
