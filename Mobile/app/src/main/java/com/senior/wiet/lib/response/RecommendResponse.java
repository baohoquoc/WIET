package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.RecommendValue;
import com.senior.wiet.lib.model.RecommendValues;

import java.util.List;

/**
 * Create by mitt on 3/9/2020.
 */
public class RecommendResponse implements Parcelable {

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
    private boolean success;

    @SerializedName("value")
    @Expose
    private RecommendValue value;

    @SerializedName("values")
    @Expose
    private List<RecommendValues> values;

    @Ignore
    public RecommendResponse() {
    }


    protected RecommendResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        success = in.readByte() != 0;
        value = in.readParcelable(RecommendValue.class.getClassLoader());
        values = in.createTypedArrayList(RecommendValues.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(error);
        dest.writeString(message);
        dest.writeInt(status);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeParcelable(value, flags);
        dest.writeTypedList(values);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecommendResponse> CREATOR = new Creator<RecommendResponse>() {
        @Override
        public RecommendResponse createFromParcel(Parcel in) {
            return new RecommendResponse(in);
        }

        @Override
        public RecommendResponse[] newArray(int size) {
            return new RecommendResponse[size];
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

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RecommendValue getValue() {
        return value;
    }

    public void setValue(RecommendValue value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<RecommendValues> getValues() {
        return values;
    }

    public void setValues(List<RecommendValues> values) {
        this.values = values;
    }

    public static Creator<RecommendResponse> getCREATOR() {
        return CREATOR;
    }
}



