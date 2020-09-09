package com.senior.wiet.lib.model.fooddetail;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

public class FoodDetailValue {
    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private int price;

    @SerializedName("store_name")
    private String store_name;

    @SerializedName("address")
    private String address;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    @SerializedName("id_food")
    private int id_food;

    @SerializedName("image")
    private String image;

    @SerializedName("is_bookmark")
    private Boolean is_bookmark;

    @Ignore
    public FoodDetailValue(){

    }

    public FoodDetailValue(String name, int price, String store_name, String address, float latitude, float longitude, int id_food, String image, Boolean is_bookmark) {
        this.name = name;
        this.price = price;
        this.store_name = store_name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id_food = id_food;
        this.image = image;
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

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
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
}
