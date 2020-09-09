package com.senior.wiet.lib.model;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.senior.wiet.BuildConfig;
import com.senior.wiet.R;
import com.senior.wiet.activities.login.LoginPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.DummyJson;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.response.LoginResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.DebugConstant;
import com.senior.wiet.utilities.Utilities;

/**
 * Created by Lance on 21/February/2020.
 */
public class LoginModel extends BaseObservable {

    private Context mContext;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;
    private DummyJson mDummyJson;
    private LoginPresenter mPresenter;

    public LoginModel(DisposableManager disposableManager, Context context, WietApiService wietApiService, DummyJson dummyJson) {
        this.mDisposableManager = disposableManager;
        this.mContext = context;
        this.mWietApiService = wietApiService;
        this.mDummyJson = dummyJson;
    }

    public void attachPresenter(LoginPresenter loginPresenter) {
        this.mPresenter = loginPresenter;
    }

    /*
     * This method handling data from server
     * @param firebase_tokenre
     * @param fcm_token
     */
    public void login(String firebase_token, String fcm_token) {
        if (BuildConfig.DUMMY_API) {
            mPresenter.destroyAPI(mDisposableManager.login(mDummyJson.getItemsFromJson(), new IDisposableListener<LoginResponse>() {

                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(LoginResponse loginResponse) {
                    Utilities.Log(DebugConstant.USER_EMAIL, loginResponse.getValue().getUser().getEmail());
                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    Utilities.Log(DebugConstant.Error, message);
                }

                @Override
                public void onApiFailure(Throwable error) {
                    Utilities.Log(DebugConstant.Error, error.getMessage());
                }
            }));
        } else {
            Account account = new Account(firebase_token, fcm_token);
            if (Utilities.isNetworkConnected(mContext)) {
                mPresenter.destroyAPI(mDisposableManager.login(mWietApiService.login(account), new IDisposableListener<LoginResponse>() {

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onHandleData(LoginResponse loginResponse) {
                        mPresenter.loginSuccess(loginResponse.getValue());
                    }

                    @Override
                    public void onRequestWrongData(int t, String message) {
                        mPresenter.loginFailure(message);
                        Utilities.Log(DebugConstant.Error, message);
                    }

                    @Override
                    public void onApiFailure(Throwable error) {
                        mPresenter.loginFailure(error.getMessage());
                        Utilities.Log(DebugConstant.Error, error.getMessage());
                    }
                }));
            } else {
                mPresenter.loginFailure(mContext.getString(R.string.error_no_internet));
            }
        }
    }
}