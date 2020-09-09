package com.senior.wiet.lib.model.history;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Values {
    @SerializedName("created_at")
    @Expose
    private String createdTime;

    @SerializedName("food_id")
    @Expose
    private int food_id;

    @SerializedName("food_name")
    @Expose
    private String food_name;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("image")
    @Expose
    private String imageURL;

    @SerializedName("uid")
    @Expose
    private String uid;

    public Values(){

    }

    public Values(String createdTime, int food_id, String food_name, int id, String imageURL, String uid) {
        this.createdTime = createdTime;
        this.food_id = food_id;
        this.food_name = food_name;
        this.id = id;
        this.imageURL = imageURL;
        this.uid = uid;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
