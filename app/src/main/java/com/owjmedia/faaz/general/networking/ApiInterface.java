package com.owjmedia.faaz.general.networking;

import com.owjmedia.faaz.data.AuthenticationRequest;
import com.owjmedia.faaz.data.AuthenticationResponse;
import com.owjmedia.faaz.data.ConfirmationRequest;
import com.owjmedia.faaz.data.ConfirmationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by salman on 11/7/17.
 */

public interface ApiInterface {

    @POST("v1/users/auth/")
    Call<AuthenticationResponse> sendPhoneNumber(@Body AuthenticationRequest authenticationRequest);

    @POST("v1/users/auth/confirm/")
    Call<ConfirmationResponse> confirmPhoneNumber(@Body ConfirmationRequest confirmationRequest);


}
