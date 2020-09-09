package com.senior.wiet.framework.di.component;

import android.app.Application;

import com.senior.wiet.framework.di.module.AppDataBindingModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by lance on 18/February/2020.
 */
@Singleton
@Component(modules = AppDataBindingModule.class)
public interface AppDataBindingComponent extends androidx.databinding.DataBindingComponent {

    @Component.Builder
    interface Builder {

        AppDataBindingComponent build();

        @BindsInstance
        Builder application(Application application);
    }
}
