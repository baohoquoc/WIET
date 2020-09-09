package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.RecommendValues;
import com.senior.wiet.lib.model.Value;

import java.util.List;

public class TopRatingResponse implements Parcelable {
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

    @SerializedName("value")    //null
    @Expose
    private Value value;

    @SerializedName("values")
    @Expose
    private List<RecommendValues> values;


    public TopRatingResponse() {
    }

    public TopRatingResponse(String error, String message, int status, Boolean succes, Value value, List<RecommendValues> values) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.succes = succes;
        this.value = value;
        this.values = values;
    }

    protected TopRatingResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        byte tmpSucces = in.readByte();
        succes = tmpSucces == 0 ? null : tmpSucces == 1;
    }

    public static final Creator<TopRatingResponse> CREATOR = new Creator<TopRatingResponse>() {
        @Override
        public TopRatingResponse createFromParcel(Parcel in) {
            return new TopRatingResponse(in);
        }

        @Override
        public TopRatingResponse[] newArray(int size) {
            return new TopRatingResponse[size];
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

    public List<RecommendValues> getValues() {
        return values;
    }

    public void setValues(List<RecommendValues> values) {
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
