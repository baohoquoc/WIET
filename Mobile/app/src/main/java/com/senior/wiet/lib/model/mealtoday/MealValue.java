package com.senior.wiet.lib.model.mealtoday;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

public class MealValue {
    @SerializedName("meal")
    private Meal meal;

    @Ignore
    public MealValue(){

    }

    public MealValue(Meal meal) {
        this.meal = meal;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
