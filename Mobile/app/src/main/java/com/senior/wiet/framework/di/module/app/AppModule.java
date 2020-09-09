package com.senior.wiet.framework.di.module.app;

import android.app.Application;

import com.senior.wiet.utilities.BaseSchedulerProvider;
import com.senior.wiet.utilities.SchedulerProvider;

import dagger.Binds;
import dagger.Module;

/**
 * Created by lance on 18/February/2020.
 */
@Module
public abstract class AppModule {

    @Binds
    abstract Application provideApplication(Application application);

    @Binds
    abstract BaseSchedulerProvider providerSchedulerProvider(SchedulerProvider provider);
}
