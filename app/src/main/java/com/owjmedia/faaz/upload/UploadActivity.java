package com.owjmedia.faaz.upload;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.utils.ActivityUtils;

/**
 * Created by salman on 1/19/18.
 */

public class UploadActivity extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_act);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), new UploadMediaFragment(), R.id.contentFrame);

    }
}
