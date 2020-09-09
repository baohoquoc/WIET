package com.senior.wiet.lib.model.mealtoday;

import androidx.room.Ignore;

import com.google.gson.annotations.SerializedName;

public class Meal {
    @SerializedName("breakfast")
    private Breakfast breakfast;

    @SerializedName("dinner")
    private Dinner dinner;

    @SerializedName("lunch")
    private Lunch lunch;

    @SerializedName("temperature")
    private Temperature temperature;

    @Ignore
    public Meal(){

    }

    public Meal(Breakfast breakfast, Dinner dinner, Lunch lunch, Temperature temperature) {
        this.breakfast = breakfast;
        this.dinner = dinner;
        this.lunch = lunch;
        this.temperature = temperature;
    }

    public Breakfast getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    public Dinner getDinner() {
        return dinner;
    }

    public void setDinner(Dinner dinner) {
        this.dinner = dinner;
    }

    public Lunch getLunch() {
        return lunch;
    }

    public void setLunch(Lunch lunch) {
        this.lunch = lunch;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
}
