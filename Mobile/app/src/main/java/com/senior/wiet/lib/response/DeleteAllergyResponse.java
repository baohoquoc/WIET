package com.senior.wiet.lib.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.senior.wiet.lib.model.DeleteAllergyValue;

import java.util.List;

public class DeleteAllergyResponse {

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
    private DeleteAllergyValue value;
    @SerializedName("values")
    @Expose
    private List<Object> values = null;

    /*
     * No args constructor for use in serialization
     *
     */
    public DeleteAllergyResponse() {
    }

    /*
     *
     * @param success
     * @param values
     * @param error
     * @param message
     * @param value
     * @param status
     */
    public DeleteAllergyResponse(String error, String message, Integer status, Boolean success, DeleteAllergyValue value, List<Object> values) {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DeleteAllergyValue getValue() {
        return value;
    }

    public void setValue(DeleteAllergyValue value) {
        this.value = value;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }
}