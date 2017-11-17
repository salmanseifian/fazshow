package com.owjmedia.faaz.authenticate.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 11/8/17.
 */

public class AuthenticationRequest {

    @SerializedName("phone")
    String mPhone;

    public AuthenticationRequest(String phone) {
       this.mPhone = phone;
    }

}
