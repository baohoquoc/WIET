package com.senior.wiet.lib.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Create by mitt on 3/9/2020.
 */
public class RecommendValue implements Parcelable {

    @SerializedName("limit")
    @Expose
    private int limit;

    @SerializedName("offset")
    @Expose
    private int offset;

    @SerializedName("total_offset")
    @Expose
    private int total_offset;

    @SerializedName("total_record")
    @Expose
    private int total_record;

    @Ignore
    public RecommendValue() {
    }

    public RecommendValue(int limit, int offset, int total_offset, int total_record) {
        this.limit = limit;
        this.offset = offset;
        this.total_offset = total_offset;
        this.total_record = total_record;
    }

    protected RecommendValue(Parcel in) {
        limit = in.readInt();
        offset = in.readInt();
        total_offset = in.readInt();
        total_record = in.readInt();
    }

    public static final Creator<RecommendValue> CREATOR = new Creator<RecommendValue>() {
        @Override
        public RecommendValue createFromParcel(Parcel in) {
            return new RecommendValue(in);
        }

        @Override
        public RecommendValue[] newArray(int size) {
            return new RecommendValue[size];
        }
    };

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal_offset() {
        return total_offset;
    }

    public void setTotal_offset(int total_offset) {
        this.total_offset = total_offset;
    }

    public int getTotal_record() {
        return total_record;
    }

    public void setTotal_record(int total_record) {
        this.total_record = total_record;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(limit);
        parcel.writeInt(offset);
        parcel.writeInt(total_offset);
        parcel.writeInt(total_record);
    }
}
