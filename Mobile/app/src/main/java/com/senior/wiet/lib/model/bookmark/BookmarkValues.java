package com.senior.wiet.lib.model.bookmark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookmarkValues {
    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("average_score")
    @Expose
    private Integer average_score;

    @SerializedName("created_time")
    @Expose
    private String created_time;

    @SerializedName("food_id")
    @Expose
    private Integer food_id;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("is_bookmarked")
    @Expose
    private Boolean is_bookmarked;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private Integer price;

    @SerializedName("restaurant_id")
    @Expose
    private Integer restaurant_id;

    @SerializedName("total_view")
    @Expose
    private Integer total_view;

    @SerializedName("total_vote")
    @Expose
    private Integer total_vote;

    @SerializedName("updated_time")
    @Expose
    private String updated_time;

    public Integer getAverage_score() {
        return average_score;
    }

    public BookmarkValues(String address,Integer average_score, String created_time, Integer food_id, Integer id, String image, Boolean is_bookmarked, String name, Integer price, Integer restaurant_id, Integer total_view, Integer total_vote, String updated_time) {
        this.address= address;
        this.average_score = average_score;
        this.created_time = created_time;
        this.food_id = food_id;
        this.id = id;
        this.image = image;
        this.is_bookmarked = is_bookmarked;
        this.name = name;
        this.price = price;
        this.restaurant_id = restaurant_id;
        this.total_view = total_view;
        this.total_vote = total_vote;
        this.updated_time = updated_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAverage_score(Integer average_score) {
        this.average_score = average_score;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public Integer getFood_id() {
        return food_id;
    }

    public void setFood_id(Integer food_id) {
        this.food_id = food_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIs_bookmarked() {
        return is_bookmarked;
    }

    public void setIs_bookmarked(Boolean is_bookmarked) {
        this.is_bookmarked = is_bookmarked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public Integer getTotal_view() {
        return total_view;
    }

    public void setTotal_view(Integer total_view) {
        this.total_view = total_view;
    }

    public Integer getTotal_vote() {
        return total_vote;
    }

    public void setTotal_vote(Integer total_vote) {
        this.total_vote = total_vote;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }
}
