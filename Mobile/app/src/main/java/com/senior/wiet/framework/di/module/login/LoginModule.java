package com.senior.wiet.framework.di.module.login;

import android.content.Context;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.senior.wiet.R;
import com.senior.wiet.activities.login.LoginActivity;
import com.senior.wiet.activities.login.LoginContract;
import com.senior.wiet.activities.login.LoginPresenter;
import com.senior.wiet.framework.di.scope.ActivityScope;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.DummyJson;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.LoginModel;
import com.senior.wiet.lib.service.WietApiService;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lance on 18/February/2020.
 */
@Module
public abstract class LoginModule {

    @Binds
    public abstract LoginContract.Presenter provideLoginPresenter(LoginPresenter presenter);

    @Provides
    @ActivityScope
    static FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @ActivityScope
    static CallbackManager provideCallbackManager() {
        return CallbackManager.Factory.create();
    }

    @Provides
    @ActivityScope
    static GoogleSignInOptions provideGoogleSignInOptions(Context context) {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    @Provides
    @ActivityScope
    static LoginModel LoginModel(DisposableManager disposableManager, Context context, WietApiService wietApiService, DummyJson dummyJson) {
        return new LoginModel(disposableManager, context, wietApiService, dummyJson);
    }

    @Provides
    @ActivityScope
    static AppSharedPreference provideAppSharedPreference(Context context) {
        return AppSharedPreference.getInstance(context);
    }

    /*
     * This method to create a ProgressBarDialog while system progress
     * @param activity
     * @return ProgressBarDialog
     */
    @Provides
    @ActivityScope
    static ProgressBarDialog progressBarDialog(LoginActivity activity) {
        return new ProgressBarDialog(activity);
    }

    @Provides
    @ActivityScope
    static AlertDialog alertDialog(){
        return new AlertDialog();
    }

}
