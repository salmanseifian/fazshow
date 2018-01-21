package com.owjmedia.faaz.upload.status;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;
import com.owjmedia.faaz.upload.status.model.UploadStatus;

/**
 * Created by salman on 1/21/18.
 */

public interface UploadStatusContract {

    interface View extends BaseView<Presenter> {

        void showUploadStatus(UploadStatus uploadStatus);
    }

    interface Presenter extends BasePresenter {

        void getUploadStatus();
    }
}
