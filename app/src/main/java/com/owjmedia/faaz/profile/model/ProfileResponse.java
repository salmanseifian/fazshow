package com.owjmedia.faaz.profile.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("year_of_birth")
    @Expose
    private int yearOfBirth;
    @SerializedName("score")
    @Expose
    private int score;
    @SerializedName("drawing_qualified")
    @Expose
    private boolean drawingQualified;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isDrawingQualified() {
        return drawingQualified;
    }

    public void setDrawingQualified(boolean drawingQualified) {
        this.drawingQualified = drawingQualified;
    }

}
