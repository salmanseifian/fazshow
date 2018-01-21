package com.owjmedia.faaz.upload.status;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

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
        new UploadStatusPresenter(this).getUploadStatus();
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
        Fragment fragment;
        if(uploadStatus.isText())
            ActivityUtils.replaceFragmentToActivitySlidly(getSupportFragmentManager(), new UploadImageFragment(), R.id.contentFrame);


//        ActivityUtils.replaceFragmentToActivitySlidly(getSupportFragmentManager(), new AgreementFragment(), R.id.contentFrame);
    }
}
