package com.owjmedia.faaz.general.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

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

    public static String getEnglishGender(Context context, String persianGender) {
        if (persianGender.equals(context.getString(R.string.male_persian)))
            return context.getString(R.string.male_english);
        else
            return context.getString(R.string.female_english);
    }

    public static String getPersianGender(Context context, String englishGender) {
        if (englishGender.equals(context.getString(R.string.male_english)))
            return context.getString(R.string.male_persian);
        else
            return context.getString(R.string.female_persian);
    }

    public static String[] getStringDate(String date) {
        return date.split("\\s+");
    }

    public static String getWeekDay(String date) {
        return date.substring(0, date.indexOf(" "));
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }

}
