package com.senior.wiet.lib.model;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;

import com.senior.wiet.activities.splash.SplashPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.response.LastLocationResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.DebugConstant;
import com.senior.wiet.utilities.Utilities;

public class SplashModel extends BaseObservable {
    private Context mContext;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;
    private SplashPresenter mPresenter;

    public SplashModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        this.mDisposableManager = disposableManager;
        this.mContext = context;
        this.mWietApiService = wietApiService;
    }

    public void attachPresenter(SplashPresenter splashPresenter) {
        this.mPresenter = splashPresenter;
    }

    public void lastLocation(String location) {
        if (Utilities.isNetworkConnected(mContext)) {
            String token = AppSharedPreference.getInstance(mContext).getAccessToken();
            Location mlocation = new Location(location);
            mPresenter.destroyAPI(mDisposableManager.lastLocation(mWietApiService.lastLocation(Constants.BEARER + token, mlocation), new IDisposableListener<LastLocationResponse>() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(LastLocationResponse lastLocationResponse) {
                    mPresenter.inputSuccess(location);
                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    Utilities.Log(DebugConstant.Error, message);
                }

                @Override
                public void onApiFailure(Throwable error) {
                    mPresenter.inputFailure(error.getMessage());
                    Utilities.Log(DebugConstant.Error, error.getMessage());
                }
            }));
        } else {
            Log.d("api", "Can't connect network");
        }
    }
}
