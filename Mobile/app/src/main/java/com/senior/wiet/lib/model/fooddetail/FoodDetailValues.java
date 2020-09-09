package com.senior.wiet.lib.model.fooddetail;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodDetailValues {

    @SerializedName("average_score")
    @Expose
    private double averageScore;

    @SerializedName("created_time")
    @Expose
    private String createdTime;

    @SerializedName("food_id")
    @Expose
    private int foodId;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("restaurant_id")
    @Expose
    private int restaurantId;

    @SerializedName("total_view")
    @Expose
    private int totalView;

    @SerializedName("total_vote")
    @Expose
    private int totalVote;

    @SerializedName("updated_time")
    @Expose
    private String updatedTime;

    @Ignore
    public FoodDetailValues(){

    }

    public FoodDetailValues(double averageScore, String createdTime, int foodId, int id, String image, String name, int price, int restaurantId, int totalView, int totalVote, String updatedTime) {
        this.averageScore = averageScore;
        this.createdTime = createdTime;
        this.foodId = foodId;
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.totalView = totalView;
        this.totalVote = totalVote;
        this.updatedTime = updatedTime;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public int getTotalVote() {
        return totalVote;
    }

    public void setTotalVote(int totalVote) {
        this.totalVote = totalVote;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}
