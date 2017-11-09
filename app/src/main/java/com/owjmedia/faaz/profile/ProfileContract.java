package com.owjmedia.faaz.profile;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;

/**
 * Created by salman on 11/9/17.
 */

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void profileUpdatedSuccessfully();

    }

    interface Presenter extends BasePresenter {

        void updateProfile(String token, String gender, String city, int year_of_birth);
    }
}
