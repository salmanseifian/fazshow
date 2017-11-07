package com.owjmedia.faaz.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by salman on 11/7/17.
 */

public interface ApiInterface {
    @POST("login")
    Call<ResponseBody> sendPhoneNumber(@Query("phone_number")  String phoneNumber);
}
