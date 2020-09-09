package com.senior.wiet.activities.splash;

import android.content.Context;
import android.widget.Toast;

import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.model.SplashModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class SplashPresenter extends BasePresenterImpl<SplashContract.View> implements SplashContract.Presenter{
    private SplashModel splashModel;
    private SplashActivity splashActivity;
    private Context mContext;


    @Inject
    public SplashPresenter(SplashActivity splashActivity, SplashModel splashModel, Context context) {
        this.splashActivity = splashActivity;
        this.splashModel = splashModel;
        this.mContext = context;
    }

    @Override
    public void onViewAdded(SplashContract.View view) {
        super.onViewAdded(view);
        getView().attachModel(this.splashModel);
        splashModel.attachPresenter(this);
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);
    }


    public void inputSuccess(String message) {
        //Toast.makeText(mContext, "Vị trí hiện tại của bạn là: \n"+message,Toast.LENGTH_SHORT).show();
    }

    public void inputFailure(String message) {
        Toast.makeText(mContext, "Không thể truy cập vị trí hiện tại của bạn. Lỗi: \n"+message,Toast.LENGTH_SHORT).show();
    }
}
