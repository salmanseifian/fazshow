package com.owjmedia.faaz.upload.status;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.LottieAnimationView;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.imagedetail.ImageFragment;
import com.owjmedia.faaz.upload.image.UploadImageFragment;
import com.owjmedia.faaz.upload.status.model.UploadStatus;

/**
 * Created by salman on 1/19/18.
 */

public class UploadActivity extends AppCompatActivity implements UploadStatusContract.View {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_act);
        initView();
        new UploadStatusPresenter(this).getUploadStatus();
    }

    private void initView() {
        ViewGroup lytUploadText = findViewById(R.id.lyt_upload_text);
        ViewGroup lytUploadImage = findViewById(R.id.lyt_upload_image);
        ViewGroup lytUploadVideo = findViewById(R.id.lyt_upload_video);
        text_done = findViewById(R.id.text_done);
        image_done = findViewById(R.id.image_done);
        video_done = findViewById(R.id.video_done);
        lytUploadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.addFragmentToActivity(
                        getSupportFragmentManager(),
                        new AgreementFragment(),
                        R.id.contentFrame
                );
            }
        });
        lytUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UploadImageFragment().show(getSupportFragmentManager(), "");
            }
        });
        lytUploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showConnectionError() {

    }

    @Override
    public void showUploadStatus(UploadStatus uploadStatus) {
        AppManager.setString(this, Constants.KEYS.AGREEMENT, uploadStatus.getFaazmetrAgreement());
        if (uploadStatus.isText()) {
            text_done.setVisibility(View.VISIBLE);
            text_done.playAnimation();
        }
        if (uploadStatus.isImage()) {
            image_done.setVisibility(View.VISIBLE);
            image_done.playAnimation();
        }
        if (uploadStatus.isVideo()) {
            video_done.setVisibility(View.VISIBLE);
            video_done.playAnimation();
        }


    }

    private LottieAnimationView text_done;
    private LottieAnimationView image_done;
    private LottieAnimationView video_done;

}
