package com.senior.wiet.lib.model;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lance on 2/March/2020.
 */

public class User {

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("dob")
    private String dob;

    @SerializedName("email")
    private String email;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("is_first_login")
    private Boolean is_first_login;

    @SerializedName("is_vegetarian")
    private Boolean is_vegetarian;

    @SerializedName("location")
    private String location;

    @SerializedName("uid")
    private String uid;

    @SerializedName("updated_at")
    private String updated_at;

    @Ignore
    public User() {
    }

    public User(String avatar, String created_at, String dob, String email, String fullname, Boolean is_first_login, Boolean is_vegetarian, String location, String uid, String updated_at) {
        this.avatar = avatar;
        this.created_at = created_at;
        this.dob = dob;
        this.email = email;
        this.fullname = fullname;
        this.is_first_login = is_first_login;
        this.is_vegetarian = is_vegetarian;
        this.location = location;
        this.uid = uid;
        this.updated_at = updated_at;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getIs_first_login() {
        return is_first_login;
    }

    public void setIs_first_login(Boolean is_first_login) {
        this.is_first_login = is_first_login;
    }

    public Boolean getIs_vegetarian() {
        return is_vegetarian;
    }

    public void setIs_vegetarian(Boolean is_vegetarian) {
        this.is_vegetarian = is_vegetarian;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
