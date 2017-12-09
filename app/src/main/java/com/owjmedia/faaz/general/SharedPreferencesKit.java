package com.owjmedia.faaz.general;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by salman on 12/5/17.
 */

public class SharedPreferencesKit {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public void init(Context context){
        sharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void setString(Context context, String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(Constants.PREFS_NAME, 0);
        return pref.getString(key, null);
    }
}
