package com.owjmedia.faaz.upload.image;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

import java.io.File;

import okhttp3.MultipartBody;

/**
 * Created by salman on 1/22/18.
 */

public interface UploadImageContract {

    interface View extends BaseView<Presenter> {
        void onImageUploadedSuccessfully();
    }

    interface Presenter extends BasePresenter {
        void uploadImage(MultipartBody.Part part);
    }
}
