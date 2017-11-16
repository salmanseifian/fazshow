package com.owjmedia.faaz.general;

import android.app.Application;

import com.onesignal.OneSignal;

/**
 * Created by salman on 11/16/17.
 */

public class Global extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.InAppAlert)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }
}
