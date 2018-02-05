package com.owjmedia.faaz.poll.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PollItem {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("image")
    @Expose
    private Object image;
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

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

}
