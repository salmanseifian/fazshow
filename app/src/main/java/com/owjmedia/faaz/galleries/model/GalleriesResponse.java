package com.owjmedia.faaz.galleries.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 11/24/17.
 */

public class GalleriesResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("updated_jalali")
    @Expose
    private String updatedJalali;
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

    public String getUpdatedJalali() {
        return updatedJalali;
    }

    public void setUpdatedJalali(String updatedJalali) {
        this.updatedJalali = updatedJalali;
    }

    public String getCreatedJalali() {
        return createdJalali;
    }

    public void setCreatedJalali(String createdJalali) {
        this.createdJalali = createdJalali;
    }

}
