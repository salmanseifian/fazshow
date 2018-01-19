package com.owjmedia.faaz.upload.agreement;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

/**
 * Created by salman on 1/19/18.
 */

public interface AgreementContract {

    interface View extends BaseView<Presenter> {
        void showAgreement();
    }

    interface Presenter extends BasePresenter {

        void getUploadAgreement();
    }
}
