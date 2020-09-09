package com.senior.wiet.lib.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Lance on 21/February/2020.
 */


public class Account {

    @SerializedName("firebase_token")
    private String firebase_token;

    @SerializedName("fcm_token")
    private String fcm_token;

    public Account(String firebase_token, String fcm_token) {
        this.firebase_token = firebase_token;
        this.fcm_token = fcm_token;
    }

    public String getFirebase_token() {
        return firebase_token;
    }

    public void setFirebase_token(String firebase_token) {
        this.firebase_token = firebase_token;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

}
