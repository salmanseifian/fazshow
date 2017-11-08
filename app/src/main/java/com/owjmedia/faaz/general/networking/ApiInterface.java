package com.owjmedia.faaz.general.networking;

import com.owjmedia.faaz.data.AuthenticationRequest;
import com.owjmedia.faaz.data.AuthenticationResponse;
import com.owjmedia.faaz.data.ConfirmationRequest;
import com.owjmedia.faaz.data.ConfirmationResponse;
import com.owjmedia.faaz.data.UpdateProfileRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by salman on 11/7/17.
 */

public interface ApiInterface {

    @POST("v1/users/auth/")
    Call<AuthenticationResponse> sendPhoneNumber(@Body AuthenticationRequest authenticationRequest);

    @POST("v1/users/auth/confirm/")
    Call<ConfirmationResponse> confirmPhoneNumber(@Body ConfirmationRequest confirmationRequest);

    @PUT("v1/users/auth/")
    Call<ResponseBody> updateProfile(@Header("Authorization") String accessToken,@Body UpdateProfileRequest updateProfileRequest);


}
