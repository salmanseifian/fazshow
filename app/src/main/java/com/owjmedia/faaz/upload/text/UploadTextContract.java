package com.owjmedia.faaz.upload.text;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;
import com.owjmedia.faaz.upload.text.model.UploadTextRequest;

/**
 * Created by salman on 1/21/18.
 */

public interface UploadTextContract {

    interface View extends BaseView<Presenter> {

        void onSuccessfullyUploaded();
    }

    interface Presenter extends BasePresenter {

        void uploadText(String title, String description);
    }
}
