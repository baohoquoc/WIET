package com.senior.wiet.lib.model;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lance on 2/March/2020.
 */
public class Value {

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("user")
    private User user;

    @Ignore
    public Value() {
    }

    public Value(String access_token, User user) {
        this.access_token = access_token;
        this.user = user;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
