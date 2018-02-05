package com.owjmedia.faaz.poll.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PollRequest {

    @SerializedName("item_id")
    @Expose
    private int itemId;

    public PollRequest(int itemId) {
        this.itemId = itemId;
    }

}
