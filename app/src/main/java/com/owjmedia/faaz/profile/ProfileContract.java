package com.owjmedia.faaz.profile;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;
import com.owjmedia.faaz.profile.model.ProfileResponse;

/**
 * Created by salman on 11/9/17.
 */

public interface ProfileContract {

    interface View extends BaseView<Presenter> {

        void profileUpdatedSuccessfully();

        void showProfile(ProfileResponse profileResponse);

    }

    interface Presenter extends BasePresenter {

        void updateProfile(String token, int gender, String city, int year_of_birth);

        void getProfile(String accessToken);
    }
}
