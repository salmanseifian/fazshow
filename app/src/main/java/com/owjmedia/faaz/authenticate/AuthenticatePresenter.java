package com.owjmedia.faaz.authenticate;


import com.owjmedia.faaz.utils.ApiClient;
import com.owjmedia.faaz.utils.ApiInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticatePresenter implements AuthenticateContract.Presenter {

    private final AuthenticateContract.View mAuthenticateView;

    public AuthenticatePresenter(AuthenticateContract.View authenticateView) {
        mAuthenticateView = authenticateView;

        mAuthenticateView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void sendPhoneNumber(String phoneNumber) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody>  call = apiService.sendPhoneNumber(phoneNumber);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mAuthenticateView.showPhoneNumeberSentSuccessfully();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                
            }
        });

    }

    @Override
    public void sendVerificationCode(String code) {

    }
}
