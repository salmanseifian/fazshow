package com.owjmedia.faaz.upload.text.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by salman on 1/21/18.
 */

public class UploadTextRequest {

    @SerializedName("subtitle")
    @Expose
    public String subtitle;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("title")
    @Expose
    public String title;

    /**
     * No args constructor for use in serialization
     */
    public UploadTextRequest() {
    }

    /**
     * @param title
     * @param description
     * @param subtitle
     */
    public UploadTextRequest(String subtitle, String description, String title) {
        super();
        this.subtitle = subtitle;
        this.description = description;
        this.title = title;
    }

}
