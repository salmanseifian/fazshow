package com.owjmedia.faaz.poll.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Poll {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("enable")
    @Expose
    private boolean enable;
    @SerializedName("items")
    @Expose
    private List<PollItem> items = null;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<PollItem> getItems() {
        return items;
    }

    public void setItems(List<PollItem> items) {
        this.items = items;
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
