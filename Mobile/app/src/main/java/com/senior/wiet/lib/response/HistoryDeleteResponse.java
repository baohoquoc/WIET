package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.history.EmptyValues;
import com.senior.wiet.lib.model.history.Value;

import java.util.List;

public class HistoryDeleteResponse implements Parcelable {

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
    private Boolean success;

    @SerializedName("value")
    @Expose
    private Value value;

    @SerializedName("values")
    @Expose
    private List<EmptyValues> values;

    public HistoryDeleteResponse() {

    }


    public HistoryDeleteResponse(String error, String message, int status, Boolean success, Value value, List<EmptyValues> values) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.success = success;
        this.value = value;
        this.values = values;
    }

    protected HistoryDeleteResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        byte tmpSucces = in.readByte();
        success = tmpSucces == 0 ? null : tmpSucces == 1;
    }

    public static final Parcelable.Creator<HistoryDeleteResponse> CREATOR = new Parcelable.Creator<HistoryDeleteResponse>() {
        @Override
        public HistoryDeleteResponse createFromParcel(Parcel in) {
            return new HistoryDeleteResponse(in);
        }

        @Override
        public HistoryDeleteResponse[] newArray(int size) {
            return new HistoryDeleteResponse[size];
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public List<EmptyValues> getValues() {
        return values;
    }

    public void setValues(List<EmptyValues> values) {
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
        dest.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
    }
}

