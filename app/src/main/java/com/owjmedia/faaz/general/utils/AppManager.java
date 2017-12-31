package com.owjmedia.faaz.general.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by salman on 11/9/17.
 */

public class AppManager {

    public static boolean isLogin(Context context) {
        return getString(context, Constants.KEYS.TOKEN) != null && !getString(context, Constants.KEYS.TOKEN).isEmpty();
    }

    public static String getToken(Context context) {
        if (getString(context, Constants.KEYS.TOKEN) != null)
            return getString(context, Constants.KEYS.TOKEN);
        else
            return "";
    }


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

    public static int getGenderNumber(Context context, String persianGender) {
        if (persianGender.equals(context.getString(R.string.male_persian)))
            return 1;
        else
            return 2;
    }


    public static String[] getStringDate(String date) {
        return date.split("\\s+");
    }

    public static String getWeekDay(String date) {
        return date.substring(0, date.indexOf(" "));
    }





}
