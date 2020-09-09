package com.senior.wiet.lib.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.InformationValue;
import com.senior.wiet.lib.model.InformationValues;

import java.util.List;

public class InformationResponse implements Parcelable{

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("value")
    @Expose
    private InformationValue informationValue;
    @SerializedName("values")
    @Expose
    private List<InformationValues> informationValues;

    public String getError() { return error; }

    public void setError(String error) { this.error = error; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public Boolean getSuccess() { return success; }

    public void setSuccess(Boolean success) { this.success = success; }

    public InformationValue getValue() { return informationValue; }

    public void setValue(InformationValue informationValue) { this.informationValue = informationValue; }

    public List<InformationValues> getValues() { return informationValues; }

    public void setValues(List<InformationValues> values) { this.informationValues = informationValues; }

    @Ignore
    public InformationResponse() {
    }

    public InformationResponse(String error, String message, int status, Boolean success, InformationValue informationValue, List<InformationValues> informationValues) {
        this.error = error;
        this.message = message;
        this.status = status;
        this.success = success;
        this.informationValue = informationValue;
        this.informationValues = informationValues;
    }

    protected InformationResponse(Parcel in) {
        error = in.readString();
        message = in.readString();
        status = in.readInt();
        byte tmpSucces = in.readByte();
        success = tmpSucces == 0 ? null : tmpSucces == 1;
    }
    public static final Parcelable.Creator<InformationResponse> CREATOR = new Parcelable.Creator<InformationResponse>() {
        @Override
        public InformationResponse createFromParcel(Parcel in) {
            return new InformationResponse(in);
        }

        @Override
        public InformationResponse[] newArray(int size) {
            return new InformationResponse[size];
        }
    };

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
