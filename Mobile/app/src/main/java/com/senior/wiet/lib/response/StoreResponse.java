package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.Store.StoreValue;
import com.senior.wiet.lib.model.Store.StoreValues;

import java.util.List;

public class StoreResponse implements Parcelable {

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
    private StoreValue storeValue;

    @SerializedName("values")
    @Expose
    private List<StoreValues> storeValues;

    public StoreResponse(){

    }

    public StoreResponse(String error, String message, int status, Boolean succes, StoreValue storeValue, List<StoreValues> storeValues) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.succes = succes;
        this.storeValue = storeValue;
        this.storeValues = storeValues;
    }

    protected StoreResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        byte tmpSucces = in.readByte();
        succes = tmpSucces == 0 ? null : tmpSucces == 1;
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

    public StoreValue getStoreValue() {
        return storeValue;
    }

    public void setStoreValue(StoreValue storeValue) {
        this.storeValue = storeValue;
    }

    public List<StoreValues> getStoreValues() {
        return storeValues;
    }

    public void setStoreValues(List<StoreValues> storeValues) {
        this.storeValues = storeValues;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(error);
        dest.writeString(message);
        dest.writeInt(status);
        dest.writeByte((byte) (succes == null ? 0 : succes ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StoreResponse> CREATOR = new Creator<StoreResponse>() {
        @Override
        public StoreResponse createFromParcel(Parcel in) {
            return new StoreResponse(in);
        }

        @Override
        public StoreResponse[] newArray(int size) {
            return new StoreResponse[size];
        }
    };
}
