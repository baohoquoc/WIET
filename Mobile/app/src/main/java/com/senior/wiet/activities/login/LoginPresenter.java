package com.senior.wiet.activities.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.senior.wiet.R;
import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.LoginModel;
import com.senior.wiet.lib.model.Value;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.DebugConstant;
import com.senior.wiet.utilities.Utilities;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by lance on 18/February/2020.
 */
public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter, OnCompleteListener {

    private final FirebaseAuth mFirebaseAuth;
    private final LoginActivity mLoginActivity;
    private final AppSharedPreference mAppSharedPreference;
    private LoginModel loginModel;
    private GoogleSignInClient mGoogleSignInClient;
    private Context mContext;
    private AlertDialog mAlertDialog;

    @Inject
    public LoginPresenter(AppSharedPreference sharedPreference, FirebaseAuth auth, LoginActivity loginActivity, GoogleSignInOptions googleSignInOptions, LoginModel loginModel, Context context) {
        this.mFirebaseAuth = auth;
        this.mLoginActivity = loginActivity;
        this.loginModel = loginModel;
        this.mAppSharedPreference = sharedPreference;
        this.mGoogleSignInClient = GoogleSignIn.getClient(loginActivity, googleSignInOptions);
        this.mContext = context;
    }

    @Override
    public void onViewAdded(LoginContract.View view) {
        super.onViewAdded(view);
        getView().attachModel(this.loginModel);
        loginModel.attachPresenter(this);
        getView().bindView().ggLoginButton.setOnClickListener(v -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            mLoginActivity.startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
        });
    }

    @Override
    public void onFacebookClicked() {
        getView().bindView().fbLoginButton.performClick();
    }

    @Override
    public void onGoogleClicked() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        mLoginActivity.startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }

    @Override
    public void handleFacebookResponse(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(mLoginActivity, this);
    }

    @Override
    public boolean handleResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                handleGoogleResponse(account);
            } catch (ApiException e) {
                Utilities.Log(DebugConstant.Error, e.getMessage());
            }
        }
        return requestCode == FacebookSdk.getCallbackRequestCodeOffset();
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);
    }

    /*
     *This method to handle data type "Mon, 1 jan 1990 gmt 00:00:00" to "01/01/1990"
     * @param date
     * @return
     */
    public String convertDateType(String date){
        date = date.substring(5, 16);
        date = date.replace(" ", "/");
        String month= date.substring(3,6);
        date = date.replace(month, convertMonth(month));
        /*if(String.valueOf(date.charAt(0)).equals("0")){
            int end = date.length();
            date = date.substring(1, end);
        }*/
        return date;
    }

    public String convertMonth(String month){
        if (month.equals("Jan")) return "01";
        else if (month.equals("Feb")) return "02";
        else if (month.equals("Mar")) return "03";
        else if (month.equals("Apr")) return "04";
        else if (month.equals("May")) return "05";
        else if (month.equals("Jun")) return "06";
        else if (month.equals("July")) return "07";
        else if (month.equals("Aug")) return "08";
        else if (month.equals("Sep")) return "09";
        else if (month.equals("Oct")) return "10";
        else if (month.equals("Nov")) return "11";
        else return "12";
    }

    @Override
    public void loginSuccess(Value value) {
        String access_token = value.getAccess_token();
        //Utilities.Log(DebugConstant.JWT_TOKEN, access_token);
        mAppSharedPreference.setAccessToken(access_token);
        mAppSharedPreference.setAvatar(value.getUser().getAvatar());
        mAppSharedPreference.setCreatedAt(value.getUser().getCreated_at());
        mAppSharedPreference.setDob(convertDateType(value.getUser().getDob()));
        mAppSharedPreference.setFullName(value.getUser().getFullname());
        mAppSharedPreference.setEmail(value.getUser().getEmail());
        mAppSharedPreference.setIsFirstLogin(value.getUser().getIs_first_login());
        mAppSharedPreference.setIsVegetarian(value.getUser().getIs_vegetarian());
        mAppSharedPreference.setUid(value.getUser().getUid());
        mAppSharedPreference.setUserLocation(value.getUser().getLocation());
        getView().hideProgressBarDialog();
        getView().navigate();
    }

    @Override
    public void loginFailure(String message) {
        getView().hideProgressBarDialog();
        getView().loginFailure(message);
    }

    @Override
    public void handleGoogleResponse(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential).addOnCompleteListener(mLoginActivity, this);
    }

    @Override
    public void onComplete(@NonNull Task task) {
        processAuthentication(task);
    }

    private void processAuthentication(final Task<AuthResult> processTask) {
        if (processTask.isSuccessful()) {
            FirebaseUser user = Objects.requireNonNull(processTask.getResult()).getUser();
            AppSharedPreference.getInstance(mContext);
            if (TextUtils.isEmpty(user.getEmail())) {
                    mAlertDialog = new AlertDialog().createDialog(mContext.getString(R.string.error_not_exist_authenticate_firebase),
                            Constants.EMPTY_STRING, mContext.getString(R.string.btn_ok));
                    mAlertDialog.setOnNegativeListener(() -> mAlertDialog.dismiss());
                    mAlertDialog.show(mLoginActivity.getSupportFragmentManager(), AlertDialog.class.getSimpleName());
            } else {
                user.getIdToken(true).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        final String firebase_token = Objects.requireNonNull(task.getResult()).getToken();
                        String fcmToken = FirebaseInstanceId.getInstance().getToken();
                        //Utilities.Log(DebugConstant.FCM_TOKEN, fcmToken);
                        AppSharedPreference.getInstance(mContext).setFcmtoken(fcmToken);
                        String fcm_token = AppSharedPreference.getInstance(mContext).getFcmtoken();
                        Utilities.Log(DebugConstant.FIREBASE_TOKEN, firebase_token);
                        loginModel.login(firebase_token, fcm_token);
                        getView().showProgressBarDialog();
                    }
                });
            }
        } else {
            getView().hideProgressBarDialog();
            getView().showMessage(mContext.getString(R.string.error_login_social_through_firebase));
        }
    }

}
