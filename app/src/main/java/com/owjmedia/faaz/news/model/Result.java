package com.owjmedia.faaz.news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 11/11/17.
 */

public class Result {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("enable")
    @Expose
    private boolean enable;
    @SerializedName("created_jalali")
    @Expose
    private String createdJalali;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getCreatedJalali() {
        return createdJalali;
    }

    public void setCreatedJalali(String createdJalali) {
        this.createdJalali = createdJalali;
    }

}