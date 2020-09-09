package com.senior.wiet.lib.model.bookmark;

import com.google.gson.annotations.SerializedName;

public class FoodId {

    @SerializedName("food_id")
    private int food_id;

    public FoodId(int food_id) {
        this.food_id = food_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }
}
