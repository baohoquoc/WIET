package com.senior.wiet.lib.model;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Create by mitt on 3/9/2020.
 */
public class RecommendRequest {
    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("is_vegatarian")
    @Expose
    private boolean is_vegatarian;

    @SerializedName("age")
    @Expose
    private int age;

    @Ignore
    public RecommendRequest() {
    }

    public RecommendRequest(String location, boolean is_vegatarian, int age) {
        this.location = location;
        this.is_vegatarian = is_vegatarian;
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isIs_vegatarian() {
        return is_vegatarian;
    }

    public void setIs_vegatarian(boolean is_vegatarian) {
        this.is_vegatarian = is_vegatarian;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
