package com.senior.wiet.lib.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.LastLocationValue;

import java.util.List;

public class LastLocationResponse {

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
    private LastLocationValue value;
    @SerializedName("values")
    @Expose
    private List<Object> values = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public LastLocationResponse() {
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
    public LastLocationResponse(String error, String message, Integer status, Boolean success, LastLocationValue value, List<Object> values) {
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

    public LastLocationResponse withError(String error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LastLocationResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LastLocationResponse withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LastLocationResponse withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public LastLocationValue getValue() {
        return value;
    }

    public void setValue(LastLocationValue value) {
        this.value = value;
    }

    public LastLocationResponse withValue(LastLocationValue value) {
        this.value = value;
        return this;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public LastLocationResponse withValues(List<Object> values) {
        this.values = values;
        return this;
    }

}