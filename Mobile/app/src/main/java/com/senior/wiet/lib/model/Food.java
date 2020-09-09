package com.senior.wiet.lib.model;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Create by mitt on 3/10/2020.
 */
public class Food {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("restaurant_id")
    @Expose
    private int restaurant_id;

    @SerializedName("food_id")
    @Expose
    private int food_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("average_score")
    @Expose
    private double average_score;

    @SerializedName("total_vote")
    @Expose
    private int total_vote;

    @SerializedName("total_view")
    @Expose
    private int total_view;

    @SerializedName("created_time")
    @Expose
    private String created_time;

    @SerializedName("updated_time")
    @Expose
    private String updated_time;

    @Ignore
    public Food() {
    }

    public Food(int id, int restaurant_id, int food_id, String name, int price, String image, double average_score, int total_vote, int total_view, String created_time, String updated_time) {
        this.id = id;
        this.restaurant_id = restaurant_id;
        this.food_id = food_id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.average_score = average_score;
        this.total_vote = total_vote;
        this.total_view = total_view;
        this.created_time = created_time;
        this.updated_time = updated_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getAverage_score() {
        return average_score;
    }

    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    public int getTotal_vote() {
        return total_vote;
    }

    public void setTotal_vote(int total_vote) {
        this.total_vote = total_vote;
    }

    public int getTotal_view() {
        return total_view;
    }

    public void setTotal_view(int total_view) {
        this.total_view = total_view;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }
}
