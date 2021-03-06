package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.Value;
import com.senior.wiet.lib.model.Values;

import java.util.List;

public class RatingResponse implements Parcelable{
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
    private Value value;

    @SerializedName("values")
    @Expose
    private List<Values> values;

    public RatingResponse() {
    }

    public RatingResponse(String error, String message, int status, Boolean succes, Value value, List<Values> values) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.succes = succes;
        this.value = value;
        this.values = values;
    }

    protected RatingResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        byte tmpSucces = in.readByte();
        succes = tmpSucces == 0 ? null : tmpSucces == 1;
    }

    public static final Parcelable.Creator<RatingResponse> CREATOR = new Parcelable.Creator<RatingResponse>() {
        @Override
        public RatingResponse createFromParcel(Parcel in) {
            return new RatingResponse(in);
        }

        @Override
        public RatingResponse[] newArray(int size) {
            return new RatingResponse[size];
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

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public List<Values> getValues() {
        return values;
    }

    public void setValues(List<Values> values) {
        this.values = values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(error);
        dest.writeString(message);
        dest.writeInt(status);
        dest.writeByte((byte) (succes == null ? 0 : succes ? 1 : 2));
    }
}
