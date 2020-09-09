package com.senior.wiet.activities.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.senior.wiet.R;
import com.senior.wiet.activities.BaseActivity;
import com.senior.wiet.activities.information.InformationActivity;
import com.senior.wiet.activities.mainscreen.MainActivity;
import com.senior.wiet.databinding.ActivityLoginBinding;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.LoginModel;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.Utilities;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Lance on 19/February/2020.
 */
public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {

    @Inject
    FirebaseAuth mFirebaseAuth;

    @Inject
    CallbackManager mCallbackManager;

    @Inject
    LoginPresenter mPresenter;

    @Inject
    Context mContext;

    @Inject
    AppSharedPreference mAppSharedPreference;

    @Inject
    ProgressBarDialog mProgressBarDialog;

    @Inject
    LoginActivity mActivity;

    private ActivityLoginBinding binding;
    private FirebaseUser mFirebaseUser;
    private Disposable mDisposable;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        super.onCreate(savedInstanceState);
        Utilities.Log("gg_user",new Gson().toJson(mFirebaseAuth.getCurrentUser()));
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mPresenter.onViewAdded(this);
        binding.setPresenter(mPresenter);
        binding.fbLoginButton.setReadPermissions(Constants.FbPermission.EMAIL, Constants.FbPermission.PUBLIC_PROFILE);
        binding.fbLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mPresenter.handleFacebookResponse(loginResult.getAccessToken());


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    public void attachModel(LoginModel loginModel) {
        binding.setModel(loginModel);
    }

    @Override
    public ActivityLoginBinding bindView() {
        return binding;
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void loginFailure(String message) {
        mAlertDialog = new AlertDialog().createDialog(message, Constants.EMPTY_STRING, mContext.getString(R.string.btn_ok));
        mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
        mAlertDialog.show(LoginActivity.this.getSupportFragmentManager(), AlertDialog.class.getSimpleName());
    }

    @Override
    public void navigate() {
        if (mAppSharedPreference.getIsFirstLogin()) {
            startActivity(new Intent(this, InformationActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mPresenter.handleResult(requestCode, resultCode, data)) {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {

        // This code to destroy API if LoginActivity is destroyed
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose();

        super.onDestroy();
    }

    @Override
    public void showMessage(String message) {
        mAlertDialog = new AlertDialog().createDialog(message,
                Constants.EMPTY_STRING, mContext.getString(R.string.btn_ok));
        mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
        mAlertDialog.show(mActivity.getSupportFragmentManager(), AlertDialog.class.getSimpleName());
    }

    @Override
    public void showProgressBarDialog() {
        if (!isDestroyed() && !mProgressBarDialog.isShowing()) {
            mProgressBarDialog.show();
        }
    }

    @Override
    public void hideProgressBarDialog() {
        if (!isDestroyed() && mProgressBarDialog.isShowing()) {
            mProgressBarDialog.dismiss();
        }
    }
}