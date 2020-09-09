package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.mealtoday.MealValue;
import com.senior.wiet.lib.model.mealtoday.MealValues;

import java.util.List;

public class MealResponse implements Parcelable {

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
    private MealValue mealValue;

    @SerializedName("values")
    @Expose
    private List<MealValues> mealValuesList;

    public MealResponse(){

    }

    public MealResponse(String error, String message, int status, Boolean succes, MealValue mealValue, List<MealValues> mealValuesList) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.succes = succes;
        this.mealValue = mealValue;
        this.mealValuesList = mealValuesList;
    }

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

    public MealValue getMealValue() {
        return mealValue;
    }

    public void setMealValue(MealValue mealValue) {
        this.mealValue = mealValue;
    }

    public List<MealValues> getMealValuesList() {
        return mealValuesList;
    }

    public void setMealValuesList(List<MealValues> mealValuesList) {
        this.mealValuesList = mealValuesList;
    }

    protected MealResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        byte tmpSucces = in.readByte();
        succes = tmpSucces == 0 ? null : tmpSucces == 1;
    }

    public static final Creator<MealResponse> CREATOR = new Creator<MealResponse>() {
        @Override
        public MealResponse createFromParcel(Parcel in) {
            return new MealResponse(in);
        }

        @Override
        public MealResponse[] newArray(int size) {
            return new MealResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(error);
        dest.writeString(message);
        dest.writeInt(status);
        dest.writeByte((byte) (succes == null ? 0 : succes ? 1 : 2));
    }
}
