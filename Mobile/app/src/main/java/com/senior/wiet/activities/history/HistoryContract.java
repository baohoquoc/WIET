package com.senior.wiet.activities.history;

import com.senior.wiet.databinding.ActivityHistoryBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.history.HistoryModel;

import io.reactivex.disposables.Disposable;

public interface HistoryContract {

    interface View extends BaseView {

        void attachModel(HistoryModel historyModel);

        ActivityHistoryBinding bindView();

        void destroyAPI(Disposable disposable);

    }

    interface Presenter extends BasePresenter<HistoryContract.View> {

        void destroyAPI(Disposable disposable);

        void deleteHistoryItem(int food_id);

        void refresh();

    }


}
