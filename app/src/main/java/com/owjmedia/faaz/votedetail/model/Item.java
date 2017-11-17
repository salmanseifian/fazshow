package com.owjmedia.faaz.votedetail.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 11/11/17.
 */

public class Item {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("voted")
    @Expose
    private boolean voted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

}
