package com.owjmedia.faaz.appinfo;

import com.owjmedia.faaz.appinfo.model.AppInfoResponse;
import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

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
