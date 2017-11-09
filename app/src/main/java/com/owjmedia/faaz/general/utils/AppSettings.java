package com.owjmedia.faaz.general.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.owjmedia.faaz.general.Constants;

/**
 * Created by salman on 11/9/17.
 */

public class AppSettings {


    public static void setString(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREFS_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREFS_NAME, 0);
        return pref.getString(key, null);
    }
}
