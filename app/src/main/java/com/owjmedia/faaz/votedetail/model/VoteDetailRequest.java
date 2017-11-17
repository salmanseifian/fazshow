package com.owjmedia.faaz.votedetail.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 11/11/17.
 */

public class VoteDetailRequest {

    @SerializedName("item_id")
    @Expose
    private int itemId;


    /**
     *
     * @param itemId
     */
    public VoteDetailRequest(int itemId) {
        super();
        this.itemId = itemId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

}
