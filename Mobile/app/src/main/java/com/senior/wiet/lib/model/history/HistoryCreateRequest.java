package com.senior.wiet.lib.model.history;

import com.google.gson.annotations.SerializedName;

public class HistoryCreateRequest {
    @SerializedName("food_id")
    private int food_id;

    public HistoryCreateRequest() {
        this.food_id = 0;
    }

    public HistoryCreateRequest(int food_id) {
        this.food_id = food_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }
}
