package com.senior.wiet.activities.mainscreen;

import com.senior.wiet.databinding.ActivityMainBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.MainModel;

/**
 * Created by lance on 18/February/2020.
 */
public interface MainContract {

    interface View extends BaseView {
        void attachModel(MainModel mainModel);
        ActivityMainBinding bindView();
    }


    interface Presenter extends BasePresenter<View> {

    }
}
