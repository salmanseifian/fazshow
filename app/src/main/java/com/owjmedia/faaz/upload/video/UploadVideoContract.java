package com.owjmedia.faaz.upload.video;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

import okhttp3.MultipartBody;

/**
 * Created by salman on 1/22/18.
 */

public interface UploadVideoContract {

    interface View extends BaseView<Presenter> {
        void onVideoUploaded();
    }

    interface Presenter extends BasePresenter {
        void uploadVideo(MultipartBody.Part part);
    }
}
