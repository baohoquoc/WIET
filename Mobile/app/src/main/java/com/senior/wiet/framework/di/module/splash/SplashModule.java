package com.senior.wiet.framework.di.module.splash;

import android.content.Context;

import com.senior.wiet.activities.splash.SplashActivity;
import com.senior.wiet.activities.splash.SplashContract;
import com.senior.wiet.activities.splash.SplashPresenter;
import com.senior.wiet.framework.di.scope.ActivityScope;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.SplashModel;
import com.senior.wiet.lib.service.WietApiService;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class SplashModule {

    @Binds
    public abstract SplashContract.Presenter provideSplashPresenter(SplashPresenter splashPresenter);

    @Provides
    @ActivityScope
    static SplashModel splashModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        return new SplashModel(disposableManager, context, wietApiService);
    }

    @Provides
    @ActivityScope
    static AppSharedPreference provideAppSharedPreference(Context context) {
        return AppSharedPreference.getInstance(context);
    }

    @Provides
    @ActivityScope
    static ProgressBarDialog progressBarDialog(SplashActivity activity) {
        return new ProgressBarDialog(activity);
    }
}
