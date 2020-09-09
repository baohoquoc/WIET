package com.senior.wiet.lib.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Create by mitt on 3/9/2020.
 */
public class RecommendValues implements Parcelable {

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

    @SerializedName("is_bookmark")
    @Expose
    private boolean isBookmark;

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

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("latitude")
    @Expose
    private double latitude;

    @SerializedName("longitude")
    @Expose
    private double longitude;

    public boolean isBookmark() {
        return isBookmark;
    }

    public void setBookmark(boolean bookmark) {
        isBookmark = bookmark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Ignore
    public RecommendValues() {
    }

    public RecommendValues(double averageScore, String createdTime, int foodId, int id, String image, boolean isBookmark, String name, int price, int restaurantId, int totalView, int totalVote, String updatedTime) {
        this.averageScore = averageScore;
        this.createdTime = createdTime;
        this.foodId = foodId;
        this.id = id;
        this.image = image;
        this.isBookmark = isBookmark;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.totalView = totalView;
        this.totalVote = totalVote;
        this.updatedTime = updatedTime;
    }

    public RecommendValues(double averageScore, String createdTime, int foodId, int id, String image, boolean isBookmark, String name, int price, int restaurantId, int totalView, int totalVote, String updatedTime, double latitude, double longitude) {
        this.averageScore = averageScore;
        this.createdTime = createdTime;
        this.foodId = foodId;
        this.id = id;
        this.image = image;
        this.isBookmark = isBookmark;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.totalView = totalView;
        this.totalVote = totalVote;
        this.updatedTime = updatedTime;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RecommendValues(double averageScore, String createdTime, int foodId, int id, String image, boolean isBookmark, String name, int price, int restaurantId, int totalView, int totalVote, String updatedTime, String description, double latitude, double longitude) {
        this.averageScore = averageScore;
        this.createdTime = createdTime;
        this.foodId = foodId;
        this.id = id;
        this.image = image;
        this.isBookmark = isBookmark;
        this.name = name;
        this.price = price;
        this.restaurantId = restaurantId;
        this.totalView = totalView;
        this.totalVote = totalVote;
        this.updatedTime = updatedTime;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected RecommendValues(Parcel in) {
        averageScore = in.readDouble();
        createdTime = in.readString();
        foodId = in.readInt();
        id = in.readInt();
        image = in.readString();
        isBookmark = in.readByte() != 0;
        name = in.readString();
        price = in.readInt();
        restaurantId = in.readInt();
        totalView = in.readInt();
        totalVote = in.readInt();
        updatedTime = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<RecommendValues> CREATOR = new Creator<RecommendValues>() {
        @Override
        public RecommendValues createFromParcel(Parcel in) {
            return new RecommendValues(in);
        }

        @Override
        public RecommendValues[] newArray(int size) {
            return new RecommendValues[size];
        }
    };

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

    public Boolean getIsBookmark() {
        return isBookmark;
    }

    public void setIsBookmark(Boolean isBookmark) {
        this.isBookmark = isBookmark;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(averageScore);
        parcel.writeString(createdTime);
        parcel.writeInt(foodId);
        parcel.writeInt(id);
        parcel.writeString(image);
        parcel.writeByte((byte) (isBookmark ? 1 : 0));
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeInt(restaurantId);
        parcel.writeInt(totalView);
        parcel.writeInt(totalVote);
        parcel.writeString(updatedTime);
        parcel.writeString(description);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }
}
