package com.senior.wiet.lib.model.mealtoday;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

public class Lunch {
    @SerializedName("food_id")
    private int food_id;

    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private String image;

    @SerializedName("is_bookmark")
    private Boolean is_bookmark;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private int price;

    @SerializedName("restaurant_id")
    private int restaurant_id;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Ignore
    public Lunch(){

    }

    public Lunch(int food_id, int id, String image, Boolean is_bookmark, String name, int price, int restaurant_id, float latitude, float longitude) {
        this.food_id = food_id;
        this.id = id;
        this.image = image;
        this.is_bookmark = is_bookmark;
        this.name = name;
        this.price = price;
        this.restaurant_id = restaurant_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIs_bookmark() {
        return is_bookmark;
    }

    public void setIs_bookmark(Boolean is_bookmark) {
        this.is_bookmark = is_bookmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
}
