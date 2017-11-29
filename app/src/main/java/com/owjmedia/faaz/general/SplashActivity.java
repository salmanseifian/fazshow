package com.owjmedia.faaz.general;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.owjmedia.faaz.BuildConfig;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.home.HomeActivity;
import com.owjmedia.faaz.news.NewsActivity;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by seifian on 11/13/17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        if (BuildConfig.HOME_SCREEN)
            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        else
            startActivity(new Intent(SplashActivity.this, NewsActivity.class));
        finish();
    }
}
