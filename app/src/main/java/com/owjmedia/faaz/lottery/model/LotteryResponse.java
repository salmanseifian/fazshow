package com.owjmedia.faaz.lottery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 12/13/17.
 */

public class LotteryResponse {
    @SerializedName("status")
    @Expose
    private String status;


    public LotteryResponse(String code) {
        this.status = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
