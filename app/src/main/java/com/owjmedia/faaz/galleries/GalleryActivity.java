package com.owjmedia.faaz.galleries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;


public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_act);
        GalleriesFragment galleriesFragment = new GalleriesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEYS.GALLEY_TYPE, getIntent().getStringExtra(Constants.KEYS.GALLEY_TYPE));
        galleriesFragment.setArguments(bundle);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), galleriesFragment, R.id.contentFrame);
    }

}
