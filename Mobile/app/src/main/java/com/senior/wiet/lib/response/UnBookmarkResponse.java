package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.unbookmark.UnBookmarkValue;
import com.senior.wiet.lib.model.unbookmark.UnBookmarkValues;

import java.util.List;

public class UnBookmarkResponse implements Parcelable {
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
    private UnBookmarkValue unBookmarkValue;

    @SerializedName("values")
    @Expose
    private List<UnBookmarkValues> unBookmarkValues;

    public UnBookmarkResponse(){

    }

    public UnBookmarkResponse(String error, String message, int status, Boolean succes, UnBookmarkValue unBookmarkValue, List<UnBookmarkValues> unBookmarkValues) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.succes = succes;
        this.unBookmarkValue = unBookmarkValue;
        this.unBookmarkValues = unBookmarkValues;
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

    public UnBookmarkValue getUnBookmarkValue() {
        return unBookmarkValue;
    }

    public void setUnBookmarkValue(UnBookmarkValue unBookmarkValue) {
        this.unBookmarkValue = unBookmarkValue;
    }

    public List<UnBookmarkValues> getUnBookmarkValues() {
        return unBookmarkValues;
    }

    public void setUnBookmarkValues(List<UnBookmarkValues> unBookmarkValues) {
        this.unBookmarkValues = unBookmarkValues;
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

    protected UnBookmarkResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        byte tmpSucces = in.readByte();
        succes = tmpSucces == 0 ? null : tmpSucces == 1;
    }

    public static final Parcelable.Creator<UnBookmarkResponse> CREATOR = new Parcelable.Creator<UnBookmarkResponse>() {
        @Override
        public UnBookmarkResponse createFromParcel(Parcel in) {
            return new UnBookmarkResponse(in);
        }

        @Override
        public UnBookmarkResponse[] newArray(int size) {
            return new UnBookmarkResponse[size];
        }
    };
}
