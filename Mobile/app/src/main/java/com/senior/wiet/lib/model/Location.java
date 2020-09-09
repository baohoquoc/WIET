package com.senior.wiet.lib.model;


import com.google.gson.annotations.SerializedName;

/**
 * Created by Lance on 21/February/2020.
 */


public class Location {

    @SerializedName("location")
    private String location;


    public Location(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
