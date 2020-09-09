package com.senior.wiet.fragments.recommend;


import com.senior.wiet.databinding.FragmentRecommendBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.RecommendModel;
import com.senior.wiet.lib.model.RecommendValues;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Create by mitt on 3/6/2020.
 */
public interface RecommendContract {

    interface View extends BaseView {
        void attachModel(RecommendModel recommendModel);

        FragmentRecommendBinding bindView();

        void destroyAPI(Disposable disposable);

        void showItemFoodByIndex(int index);

        void setList(List<RecommendValues> list);

        void choiceItemFoodByIndex();

        void init();

    }

    interface Presenter extends BasePresenter<View> {
        void destroyAPI(Disposable disposable);

        void getValues(List<RecommendValues> recommendValues);

        void setDisposable(Disposable disposable);

        void getRecommendByLocation();

        void initFirstRecommend();

        void createHistory(int id_food);

        //void onShowClick();
    }
}
