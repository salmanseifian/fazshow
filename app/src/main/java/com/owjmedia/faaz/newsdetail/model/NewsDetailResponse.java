package com.owjmedia.faaz.newsdetail.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by seifian on 11/12/17.
 */

public class NewsDetailResponse {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("lead")
    @Expose
    private String lead;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("liked")
    @Expose
    private boolean liked;
    @SerializedName("news_categories")
    @Expose
    private String newsCategories;
    @SerializedName("image")
    @Expose
    private String image;
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

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsCategories() {
        return newsCategories;
    }

    public void setNewsCategories(String newsCategories) {
        this.newsCategories = newsCategories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedJalali() {
        return createdJalali;
    }

    public void setCreatedJalali(String createdJalali) {
        this.createdJalali = createdJalali;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}