package com.senior.wiet.lib.model.Store;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

public class StoreValue {
    @SerializedName("address")
    private String address;

    @SerializedName("category")
    private String category;

    @SerializedName("food_name")
    private String food_name;

    @SerializedName("id")
    private int id;

    @SerializedName("id_food_category")
    private int id_food_category;

    @SerializedName("image_path")
    private String image_path;

    @SerializedName("latitude")
    private float latitude;

    @SerializedName("longitude")
    private float longitude;

    @SerializedName("menu")
    private String menu;

    @SerializedName("rate")
    private double rate;

    @SerializedName("restaurant_id")
    private int restaurant_id;

    @SerializedName("totalreview")
    private int totalreview;

    @SerializedName("view")
    private int view;

    @SerializedName("worktime")
    private String worktime;

    @Ignore
    public StoreValue(){

    }

    public StoreValue(String address, String category, String food_name, int id, int id_food_category, String image_path, float latitude, float longitude, String menu, double rate, int restaurant_id, int totalreview, int view, String worktime) {
        this.address = address;
        this.category = category;
        this.food_name = food_name;
        this.id = id;
        this.id_food_category = id_food_category;
        this.image_path = image_path;
        this.latitude = latitude;
        this.longitude = longitude;
        this.menu = menu;
        this.rate = rate;
        this.restaurant_id = restaurant_id;
        this.totalreview = totalreview;
        this.view = view;
        this.worktime = worktime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getId_food_category() {
        return id_food_category;
    }

    public void setId_food_category(int id_food_category) {
        this.id_food_category = id_food_category;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
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

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getTotalreview() {
        return totalreview;
    }

    public void setTotalreview(int totalreview) {
        this.totalreview = totalreview;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }
}
