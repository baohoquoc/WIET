package com.senior.wiet.activities.splash;

import com.senior.wiet.databinding.ActivitySplashBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.SplashModel;

import io.reactivex.disposables.Disposable;

public class SplashContract  {

    interface View extends BaseView {
        void attachModel(SplashModel SplashModel);

        ActivitySplashBinding bindView();

        void destroyAPI(Disposable disposable);
    }

    public interface Presenter extends BasePresenter<View> {

        void destroyAPI(Disposable disposable);

        void inputSuccess(String message);

        void inputFailure(String message);
    }
}
