package com.owjmedia.faaz.authenticate.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticationResponse {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("expires_in")
    @Expose
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
