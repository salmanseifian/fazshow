package com.owjmedia.faaz.lottery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 12/13/17.
 */

public class LotteryRequest {
    @SerializedName("code")
    @Expose
    private String code;


    public LotteryRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
