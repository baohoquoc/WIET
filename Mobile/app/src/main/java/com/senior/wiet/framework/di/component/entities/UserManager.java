package com.senior.wiet.framework.di.component.entities;


import android.app.Activity;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by lance on 18/February/2020.
 */
@Singleton
public class UserManager implements HasActivityInjector {


    @Inject DispatchingAndroidInjector<Activity> activityInjector;

    @Inject
    public UserManager() {
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }
}
