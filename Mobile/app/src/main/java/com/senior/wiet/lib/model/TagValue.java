package com.senior.wiet.lib.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagValue {

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("total_offset")
    @Expose
    private Integer totalOffset;
    @SerializedName("total_record")
    @Expose
    private Integer totalRecord;

    /**
     * No args constructor for use in serialization
     *
     */
    public TagValue() {
    }

    /**
     *
     * @param offset
     * @param limit
     * @param totalRecord
     * @param totalOffset
     */
    public TagValue(Integer limit, Integer offset, Integer totalOffset, Integer totalRecord) {
        super();
        this.limit = limit;
        this.offset = offset;
        this.totalOffset = totalOffset;
        this.totalRecord = totalRecord;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalOffset() {
        return totalOffset;
    }

    public void setTotalOffset(Integer totalOffset) {
        this.totalOffset = totalOffset;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

}