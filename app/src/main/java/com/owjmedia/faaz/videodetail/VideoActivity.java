package com.owjmedia.faaz.videodetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;

/**
 * Created by salman on 12/26/17.
 */

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_act);
        Fragment fragment = new VideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEYS.VIDEO_PATH, "http://94.182.227.211/media/video_gallery/videos/KidsFest_s4He0Xt.mp4");
        fragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
    }
}
