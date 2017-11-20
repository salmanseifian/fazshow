package com.owjmedia.faaz.authenticate;


import com.owjmedia.faaz.authenticate.model.AuthenticationRequest;
import com.owjmedia.faaz.authenticate.model.AuthenticationResponse;
import com.owjmedia.faaz.authenticate.model.ConfirmationRequest;
import com.owjmedia.faaz.authenticate.model.ConfirmationResponse;
import com.owjmedia.faaz.general.networking.ApiClient;
import com.owjmedia.faaz.general.networking.ApiInterface;

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
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(phoneNumber);
        Call<AuthenticationResponse> call = apiService.sendPhoneNumber(authenticationRequest);
        mAuthenticateView.setLoadingIndicator(true);
        call.enqueue(new Callback<AuthenticationResponse>() {
            @Override
            public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                mAuthenticateView.setLoadingIndicator(false);
                if (response.code() == 200)
                    mAuthenticateView.showPhoneNumberSentSuccessfully(response.body().getToken(), response.body().getExpiresIn());
                else
                    mAuthenticateView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                mAuthenticateView.setLoadingIndicator(false);
            }
        });
    }

    @Override
    public void confirmPhoneNumber(String token, int code) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        ConfirmationRequest confirmationRequest = new ConfirmationRequest(token, code);
        Call<ConfirmationResponse> call = apiService.confirmPhoneNumber(confirmationRequest);

        call.enqueue(new Callback<ConfirmationResponse>() {
            @Override
            public void onResponse(Call<ConfirmationResponse> call, Response<ConfirmationResponse> response) {
                if (response.code() == 200)
                    mAuthenticateView.showAuthenticationCompleted(response.body().getAccessToken());
                else
                    mAuthenticateView.showMessage(response.message());
            }

            @Override
            public void onFailure(Call<ConfirmationResponse> call, Throwable t) {
            }
        });
    }

}
