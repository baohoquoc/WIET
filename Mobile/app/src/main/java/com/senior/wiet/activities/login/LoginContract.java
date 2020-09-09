package com.senior.wiet.activities.login;

import android.content.Intent;

import com.facebook.AccessToken;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.senior.wiet.databinding.ActivityLoginBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.LoginModel;
import com.senior.wiet.lib.model.Value;

import io.reactivex.disposables.Disposable;

/**
 * Created by lance on 18/February/2020.
 */
public interface LoginContract {

    interface View extends BaseView {
        void attachModel(LoginModel loginModel);

        ActivityLoginBinding bindView();

        void destroyAPI(Disposable disposable);

        void loginFailure(String message);

        void navigate();
    }


    interface Presenter extends BasePresenter<View> {

        void onFacebookClicked();

        void onGoogleClicked();

        void handleFacebookResponse(AccessToken token);

        void handleGoogleResponse(GoogleSignInAccount account);

        boolean handleResult(int requestCode, int resultCode, Intent data);

        void destroyAPI(Disposable disposable);

        void loginSuccess(Value value);

        void loginFailure(String message);
    }
}
