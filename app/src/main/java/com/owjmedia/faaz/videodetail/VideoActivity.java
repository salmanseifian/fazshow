package com.owjmedia.faaz.videodetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;

import butterknife.BindView;

/**
 * Created by salman on 12/26/17.
 */

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_act);
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Set up the toolbar.
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayShowTitleEnabled(false);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        Fragment fragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEYS.VIDEO_PATH, AppManager.getString(this, Constants.KEYS.HOMEPAGE_VIDEO));
        fragment.setArguments(bundle);
        ActivityUtils.replaceFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
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

}
