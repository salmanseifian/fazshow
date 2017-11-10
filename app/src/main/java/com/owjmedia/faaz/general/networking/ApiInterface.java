package com.owjmedia.faaz.general.networking;

import com.owjmedia.faaz.data.AuthenticationRequest;
import com.owjmedia.faaz.data.AuthenticationResponse;
import com.owjmedia.faaz.data.ConfirmationRequest;
import com.owjmedia.faaz.data.ConfirmationResponse;
import com.owjmedia.faaz.data.UpdateProfileRequest;
import com.owjmedia.faaz.data.VotingResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by salman on 11/7/17.
 */

public interface ApiInterface {

    @POST("v1/users/auth/")
    Call<AuthenticationResponse> sendPhoneNumber(@Body AuthenticationRequest authenticationRequest);

    @POST("v1/users/auth/confirm/")
    Call<ConfirmationResponse> confirmPhoneNumber(@Body ConfirmationRequest confirmationRequest);

    @PUT("v1/users/auth/")
    Call<ResponseBody> updateProfile(@Header("Authorization") String accessToken, @Body UpdateProfileRequest updateProfileRequest);

    @GET("v1/polls/")
    Call<List<VotingResponse>> getVotings(@Query("poll_type") String pollType);
}
