package com.owjmedia.faaz.upload.status;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.owjmedia.faaz.BuildConfig;
import com.owjmedia.faaz.R;
import com.owjmedia.faaz.general.Constants;
import com.owjmedia.faaz.general.utils.ActivityUtils;
import com.owjmedia.faaz.general.utils.AppManager;
import com.owjmedia.faaz.general.utils.CustomWidgets.TypefaceTextView;
import com.owjmedia.faaz.upload.OnUploaded;
import com.owjmedia.faaz.upload.video.UploadVideoFragment;
import com.owjmedia.faaz.upload.status.model.UploadStatus;
import com.owjmedia.faaz.upload.image.UploadImageFragment;


public class UploadActivity extends AppCompatActivity implements UploadStatusContract.View, OnUploaded {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_act);
        initView();
        new UploadStatusPresenter(this).getUploadStatus();
    }

    private void initView() {
        txtUploadSubtitle = findViewById(R.id.txt_upload_subtitle);
        lytUploadText = findViewById(R.id.lyt_upload_text);
        lytUploadImage = findViewById(R.id.lyt_upload_image);
        lytUploadVideo = findViewById(R.id.lyt_upload_video);
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
                new UploadVideoFragment().show(getSupportFragmentManager(), "");

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtUploadSubtitle.setText(
                    Html.fromHtml(uploadStatus.getFaazmetrDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            txtUploadSubtitle.setText(
                    Html.fromHtml(uploadStatus.getFaazmetrDescription()));
        }
        if (uploadStatus.isText()) {
            lytUploadText.setEnabled(false);
            text_done.setVisibility(View.VISIBLE);
            text_done.playAnimation();
        }
        if (!uploadStatus.isText()) {
            lytUploadImage.setEnabled(false);
            lytUploadVideo.setEnabled(false);
        }
        if (uploadStatus.isImage()) {
            lytUploadImage.setEnabled(false);
            image_done.setVisibility(View.VISIBLE);
            image_done.playAnimation();
        }
        if (uploadStatus.isVideo()) {
            lytUploadVideo.setEnabled(false);
            video_done.setVisibility(View.VISIBLE);
            video_done.playAnimation();
        }


    }

    @Override
    public void onVideoUploaded() {
        lytUploadVideo.setEnabled(false);
        video_done.setVisibility(View.VISIBLE);
        video_done.playAnimation();
    }

    @Override
    public void onImageUploaded() {
        lytUploadImage.setEnabled(false);
        image_done.setVisibility(View.VISIBLE);
        image_done.playAnimation();
    }


    private TypefaceTextView txtUploadSubtitle;
    private LottieAnimationView text_done;
    private LottieAnimationView image_done;
    private LottieAnimationView video_done;
    private ViewGroup lytUploadText;
    private ViewGroup lytUploadImage;
    private ViewGroup lytUploadVideo;

}
