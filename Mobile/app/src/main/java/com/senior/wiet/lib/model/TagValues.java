package com.senior.wiet.lib.model;

import com.google.gson.annotations.SerializedName;

public class TagValues {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("imageURL")
    private String imageURL;


    public TagValues() {
    }

    public TagValues(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagValues(int id, String name, String imageURL) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}