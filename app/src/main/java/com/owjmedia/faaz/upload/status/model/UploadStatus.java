package com.owjmedia.faaz.upload.status.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 1/21/18.
 */


public class UploadStatus {

    @SerializedName("text")
    @Expose
    private boolean text;
    @SerializedName("image")
    @Expose
    private boolean image;
    @SerializedName("video")
    @Expose
    private boolean video;
    @SerializedName("faazmetr_agreement")
    @Expose
    private String faazmetrAgreement;
    @SerializedName("faazmetr_description")
    @Expose
    private String faazmetrDescription;

    public boolean isText() {
        return text;
    }

    public void setText(boolean text) {
        this.text = text;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getFaazmetrAgreement() {
        return faazmetrAgreement;
    }

    public void setFaazmetrAgreement(String faazmetrAgreement) {
        this.faazmetrAgreement = faazmetrAgreement;
    }

    public String getFaazmetrDescription() {
        return faazmetrDescription;
    }

    public void setFaazmetrDescription(String faazmetrDescription) {
        this.faazmetrDescription = faazmetrDescription;
    }
}
