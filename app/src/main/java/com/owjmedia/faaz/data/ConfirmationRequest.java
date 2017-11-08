package com.owjmedia.faaz.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 11/8/17.
 */

public class ConfirmationRequest {

    @SerializedName("token")
    private String mToken;
    @SerializedName("code")
    private int mCode;

    public ConfirmationRequest(String token, int code) {
        this.mToken = token;
        this.mCode = code;
    }

}
