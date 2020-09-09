package com.senior.wiet.fragments.meal;

import android.content.Context;

import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.FoodModel;
import com.senior.wiet.lib.model.MealModel;
import com.senior.wiet.lib.model.TemperatureModel;
import com.senior.wiet.lib.model.mealtoday.Meal;
import com.senior.wiet.lib.model.mealtoday.MealValue;
import com.senior.wiet.utilities.Constants;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Create by mitt on 3/6/2020.
 */


public class MealPresenter extends BasePresenterImpl<MealContract.View> implements MealContract.Presenter {

    private Context mContext;
    private MealModel mealModel;
    private FoodModel foodModel;
    private AppSharedPreference mAppSharedPreference;
    MealContract.View view;
    private int temp;

    @Inject
    public MealPresenter(Context context, MealModel mealModel, AppSharedPreference appSharedPreference) {
        this.mContext = context;
        this.mealModel = mealModel;
        this.mAppSharedPreference = appSharedPreference;
    }

    public MealPresenter(MealContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewAdded(MealContract.View view) {
        super.onViewAdded(view);
        this.view = view;
        mealModel.attachPresenter(this);
        getView().attachModel(this.mealModel);
        //mealModel.attachPresenter(this);
        getWeatherByAPI();
        getMealToday();
    }


    @Override
    public void getWeatherByAPI() {
        double rLat = mAppSharedPreference.getRealLat();
        double rLon = mAppSharedPreference.getRealLong();
        mealModel.getWeatherByAPI((float)rLat, (float) rLon, Constants.UNITS, Constants.APPID);
    }

    @Override
    public void getMealToday() {
        String token = AppSharedPreference.getInstance(mContext).getAccessToken();
        temp = mAppSharedPreference.getTemp();
        mealModel.mealtoday(token, temp);
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);
    }

    @Override
    public void tempSuccess(TemperatureModel temperatureModel) {
    }

    @Override
    public void mealTodaySuccess(MealValue mealValue) {
        mAppSharedPreference.setBmeal(mealValue.getMeal().getBreakfast().getName());
        mAppSharedPreference.setLmeal(mealValue.getMeal().getLunch().getName());
        mAppSharedPreference.setDmeal(mealValue.getMeal().getDinner().getName());
        mAppSharedPreference.setTmeal(mealValue.getMeal().getTemperature().getName());

        mAppSharedPreference.setBimage(mealValue.getMeal().getBreakfast().getImage());
        mAppSharedPreference.setLimage(mealValue.getMeal().getLunch().getImage());
        mAppSharedPreference.setDimage(mealValue.getMeal().getDinner().getImage());
        mAppSharedPreference.setTimage(mealValue.getMeal().getTemperature().getImage());
    }

    @Override
    public void onBreakfastClick() {
        view.onBreakfastClick1();
    }

    @Override
    public void onLunchClick() {
        view.onLunchClick1();
    }

    @Override
    public void onDinnerClick() {
        view.onDinnerClick1();
    }

    @Override
    public void onTemperatureClick() {
        view.onTemperatureClick1();
    }

    @Override
    public void updatePlaceHolderFood(Meal meal) {
        view.updatePlaceHolderFood(meal);
    }

}
