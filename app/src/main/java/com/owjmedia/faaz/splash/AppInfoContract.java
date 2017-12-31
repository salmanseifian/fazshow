package com.owjmedia.faaz.splash;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;
import com.owjmedia.faaz.splash.model.AppInfoResponse;

/**
 * Created by salman on 12/10/17.
 */

public interface AppInfoContract {

    interface View extends BaseView<Presenter> {
        void showAppInfo(AppInfoResponse appInfoResponse);
    }

    interface Presenter extends BasePresenter {
        void getAppInfo();
    }
}
