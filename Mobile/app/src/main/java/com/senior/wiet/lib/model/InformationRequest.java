package com.senior.wiet.lib.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InformationRequest {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("is_vegetarian")
    @Expose
    private Boolean isVegetarian;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("location")
    @Expose
    private String location;

    /**
     * No args constructor for use in serialization
     *
     */
    public InformationRequest() {
    }

    /**
     *
     * @param uid
     * @param dob
     * @param isVegetarian
     * @param fullname
     * @param avatar
     * @param email
     */
    public InformationRequest(String uid, String email, String dob, String fullname, Boolean isVegetarian, String avatar, String location) {
        super();
        this.uid = uid;
        this.email = email;
        this.dob = dob;
        this.fullname = fullname;
        this.isVegetarian = isVegetarian;
        this.avatar = avatar;
        this.location = location;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String avatar) {
        this.location = location;
    }
}