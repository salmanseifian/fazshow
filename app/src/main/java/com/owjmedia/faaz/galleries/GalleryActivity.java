package com.owjmedia.faaz.galleries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_act);
        ButterKnife.bind(this);

        // Set up the toolbar.
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        GalleriesFragment galleriesFragment = new GalleriesFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.KEYS.IMAGE_GALLERY, getIntent().getBooleanExtra(Constants.KEYS.IMAGE_GALLERY, true));
        galleriesFragment.setArguments(bundle);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), galleriesFragment, R.id.contentFrame);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

}
