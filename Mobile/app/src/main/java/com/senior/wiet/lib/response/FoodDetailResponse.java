package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.fooddetail.FoodDetailValue;
import com.senior.wiet.lib.model.fooddetail.FoodDetailValues;

import java.util.List;

public class FoodDetailResponse implements Parcelable {
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("success")
    @Expose
    private Boolean succes;

    @SerializedName("value")
    @Expose
    private FoodDetailValue foodDetailValue;

    @SerializedName("values")
    @Expose
    private List<FoodDetailValues> foodDetailValuesList;

    public FoodDetailResponse(){

    }

    public FoodDetailResponse(String error, String message, int status, Boolean succes, FoodDetailValue foodDetailValue, List<FoodDetailValues> foodDetailValuesList) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.succes = succes;
        this.foodDetailValue = foodDetailValue;
        this.foodDetailValuesList = foodDetailValuesList;
    }

    protected FoodDetailResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        byte tmpSucces = in.readByte();
        succes = tmpSucces == 0 ? null : tmpSucces == 1;
    }

    public static final Creator<FoodDetailResponse> CREATOR = new Creator<FoodDetailResponse>() {
        @Override
        public FoodDetailResponse createFromParcel(Parcel in) {
            return new FoodDetailResponse(in);
        }

        @Override
        public FoodDetailResponse[] newArray(int size) {
            return new FoodDetailResponse[size];
        }
    };

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Boolean getSucces() {
        return succes;
    }

    public void setSucces(Boolean succes) {
        this.succes = succes;
    }

    public FoodDetailValue getFoodDetailValue() {
        return foodDetailValue;
    }

    public void setFoodDetailValue(FoodDetailValue foodDetailValue) {
        this.foodDetailValue = foodDetailValue;
    }

    public List<FoodDetailValues> getFoodDetailValuesList() {
        return foodDetailValuesList;
    }

    public void setFoodDetailValuesList(List<FoodDetailValues> foodDetailValuesList) {
        this.foodDetailValuesList = foodDetailValuesList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(error);
        parcel.writeString(message);
        parcel.writeInt(status);
        parcel.writeByte((byte) (succes == null ? 0 : succes ? 1 : 2));
    }
}
