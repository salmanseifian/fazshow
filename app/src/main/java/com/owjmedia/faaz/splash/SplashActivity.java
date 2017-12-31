package com.owjmedia.faaz.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.owjmedia.faaz.BuildConfig;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.home.HomeActivity;
import com.owjmedia.faaz.news.NewsActivity;
import com.crashlytics.android.Crashlytics;
import com.owjmedia.faaz.splash.model.AppInfoResponse;

import io.fabric.sdk.android.Fabric;

/**
 * Created by seifian on 11/13/17.
 */

public class SplashActivity extends AppCompatActivity implements AppInfoContract.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        new AppInfoPresenter(this).getAppInfo();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMessage(String message) {
        ActivityUtils.showToast(this, message, "emoji_wink.json");
    }

    @Override
    public void showConnectionError() {
        ActivityUtils.showToast(this, getString(R.string.no_internet_connection), "emoji_wink.json");
    }

    @Override
    public void showAppInfo(AppInfoResponse appInfoResponse) {
        AppManager.setString(this, Constants.KEYS.ABOUT_US, appInfoResponse.getAboutUs());
        AppManager.setString(this, Constants.KEYS.HOMEPAGE_VIDEO, appInfoResponse.getHomepageVideo());

        if (BuildConfig.HOME_SCREEN)
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        else
            startActivity(new Intent(SplashActivity.this, NewsActivity.class));
        finish();
    }
}
