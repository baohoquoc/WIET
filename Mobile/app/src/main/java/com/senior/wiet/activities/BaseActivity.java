package com.senior.wiet.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Lance on 19/February/2020.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView,
        HasSupportFragmentInjector {

    public T getPresenter() {
        return presenter;
    }

    @Inject
    DispatchingAndroidInjector<Fragment> injector;

    @Inject
    T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        androidInject();
        super.onCreate(savedInstanceState);
        presenter.onViewAdded(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForCrashes();
    }

    protected void androidInject() {
        AndroidInjection.inject(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onViewRemoved();
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }

    private void checkForCrashes() {
        AppCenter.start(getApplication(), "b53136b9-c309-4448-bae0-bf14338756d1",
                Analytics.class, Crashes.class);
    }

}