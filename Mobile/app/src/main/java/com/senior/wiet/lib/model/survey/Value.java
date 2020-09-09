package com.senior.wiet.lib.model.survey;

import com.google.gson.annotations.SerializedName;

public class Value {
    @SerializedName("limit")
    private int limit;
    @SerializedName("offset")
    private int offset;
    @SerializedName("total_offset")
    private int total_offset;
    @SerializedName("total_record")
    private int total_record;

    public Value(){}

    public Value(int limit, int offset, int total_offset, int total_record) {
        this.limit = limit;
        this.offset = offset;
        this.total_offset = total_offset;
        this.total_record = total_record;
    }

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
}
