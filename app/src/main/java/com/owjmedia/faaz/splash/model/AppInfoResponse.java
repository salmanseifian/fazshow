package com.owjmedia.faaz.splash.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 12/10/17.
 */

public class AppInfoResponse {

    @SerializedName("youtube")
    @Expose
    private String youtube;
    @SerializedName("instagram")
    @Expose
    private String instagram;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("telegram")
    @Expose
    private String telegram;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("about_us")
    @Expose
    private String aboutUs;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("tell")
    @Expose
    private String tell;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("homepage_video")
    @Expose
    private String homepageVideo;
    @SerializedName("homepage_image")
    @Expose
    private String homepageImage;
    @SerializedName("updated_jalali")
    @Expose
    private String updatedJalali;
    @SerializedName("created_jalali")
    @Expose
    private String createdJalali;

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getHomepageVideo() {
        return homepageVideo;
    }

    public void setHomepageVideo(String homepageVideo) {
        this.homepageVideo = homepageVideo;
    }

    public String getHomepageImage() {
        return homepageImage;
    }

    public void setHomepageImage(String homepageImage) {
        this.homepageImage = homepageImage;
    }
}
