package com.owjmedia.faaz.general;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.crashlytics.android.Crashlytics;
import com.onesignal.OneSignal;
import com.owjmedia.faaz.general.utils.AppManager;

import io.fabric.sdk.android.Fabric;

/**
 * Created by salman on 11/16/17.
 */

public class Global extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fabric.with(this, new Crashlytics());
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.InAppAlert)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    public static Global getInstance() {
        return instance;
    }

    public static boolean hasNetwork() {
        return instance.checkIfHasNetwork();
    }

    public boolean checkIfHasNetwork() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean isLogin() {
        return AppManager.getString(instance, Constants.KEYS.TOKEN) != null && !AppManager.getString(instance, Constants.KEYS.TOKEN).isEmpty();
    }

    public static String getToken() {
        if (AppManager.getString(instance, Constants.KEYS.TOKEN) != null)
            return AppManager.getString(instance, Constants.KEYS.TOKEN);
        else
            return "";
    }

    public static String getArVideo() {
        if (AppManager.getString(instance, Constants.KEYS.AR_VIDEO_PATH) != null)
            return AppManager.getString(instance, Constants.KEYS.AR_VIDEO_PATH);
        else
            return "";
    }

    private static Global instance;
}
