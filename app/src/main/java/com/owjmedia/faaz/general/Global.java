package com.owjmedia.faaz.general;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.onesignal.OneSignal;

/**
 * Created by salman on 11/16/17.
 */

public class Global extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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

    private static Global instance;
}
