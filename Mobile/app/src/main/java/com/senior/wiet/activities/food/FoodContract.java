package com.senior.wiet.activities.food;

import com.senior.wiet.databinding.ActivityFoodBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.FoodModel;
import com.senior.wiet.lib.model.fooddetail.FoodDetailValue;

import io.reactivex.disposables.Disposable;

/**
 * Created by lance on 18/February/2020.
 */
public interface  FoodContract{
    interface View extends BaseView{
        void attachModel(FoodModel foodModel);

        ActivityFoodBinding bindView();

        void destroyAPI(Disposable disposable);

        void foodFailure(String message);

        void updatePlaceHolderFood(FoodDetailValue foodDetailValue);

        void initListRecommend();
    }
    interface Presenter extends BasePresenter<View>{
        void destroyAPI(Disposable disposable);

        void foodSuccess(FoodDetailValue foodDetailValue);

        void foodFailure(String message);

        void onBookmarkClick();

        void onMapsClick();

        void onNowClick();

        void updatePlaceHolderFood(FoodDetailValue foodDetailValue);
    }
}
