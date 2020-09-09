package com.senior.wiet.fragments.meal;

import com.senior.wiet.databinding.FragmentMealBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.MealModel;
import com.senior.wiet.lib.model.TemperatureModel;
import com.senior.wiet.lib.model.mealtoday.Meal;
import com.senior.wiet.lib.model.mealtoday.MealValue;

import io.reactivex.disposables.Disposable;

/**
 * Create by mitt on 3/6/2020.
 */
public interface MealContract {

    interface View extends BaseView {
        void attachModel(MealModel mealModel);

        FragmentMealBinding bindView();

        void destroyAPI(Disposable disposable);

        void mealFailure(String message);

        void onBreakfastClick1();

        void onLunchClick1();

        void onDinnerClick1();

        void onTemperatureClick1();

        void updatePlaceHolderFood(Meal meal);

        void refresh();
    }

    interface Presenter extends BasePresenter<View> {
        void getWeatherByAPI();

        void getMealToday();

        void destroyAPI(Disposable disposable);

        void tempSuccess(TemperatureModel temperatureModel);

        void mealTodaySuccess(MealValue mealValue);

        void onBreakfastClick();

        void onLunchClick();

        void onDinnerClick();

        void onTemperatureClick();

        void updatePlaceHolderFood(Meal meal);

    }
}
