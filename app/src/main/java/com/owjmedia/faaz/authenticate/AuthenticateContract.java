package com.owjmedia.faaz.authenticate;

import com.owjmedia.faaz.general.BasePresenter;
import com.owjmedia.faaz.general.BaseView;



public interface AuthenticateContract {

    interface View extends BaseView<Presenter>{

        void showPhoneNumberSentSuccessfully(String token, int expire_in);

        void showAuthenticationCompleted(String accessToken);


    }

    interface Presenter extends BasePresenter{

        void sendPhoneNumber(String phoneNumber);

        void confirmPhoneNumber(String token, int code);

    }
}
