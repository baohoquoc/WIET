package com.senior.wiet.lib.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.AllergyValue;
import com.senior.wiet.lib.model.AllergyValues;

import java.util.List;

public class AllergyResponse {

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
    private AllergyValue value;
    @SerializedName("values")
    @Expose
    private List<AllergyValues> values = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AllergyResponse() {
    }

    /**
     *
     * @param success
     * @param values
     * @param error
     * @param message
     * @param value
     * @param status
     */
    public AllergyResponse(String error, String message, Integer status, Boolean success, AllergyValue value, List<AllergyValues> values) {
        super();
        this.error = error;
        this.message = message;
        this.status = status;
        this.success = success;
        this.value = value;
        this.values = values;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public AllergyResponse withError(String error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AllergyResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AllergyResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public AllergyResponse withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public AllergyValue getValue() {
        return value;
    }

    public void setValue(AllergyValue value) {
        this.value = value;
    }

    public AllergyResponse withValue(AllergyValue value) {
        this.value = value;
        return this;
    }

    public List<AllergyValues> getValues() {
        return values;
    }

    public void setValues(List<AllergyValues> values) {
        this.values = values;
    }

    public AllergyResponse withValues(List<AllergyValues> values) {
        this.values = values;
        return this;
    }

}