package com.senior.wiet.framework.di.component.app;

import android.app.Application;
import android.content.Context;

import com.senior.wiet.WietApplication;
import com.senior.wiet.framework.di.binding.AppBindingModule;
import com.senior.wiet.framework.di.module.app.ApiModule;
import com.senior.wiet.framework.di.module.app.AppModule;
import com.senior.wiet.framework.di.module.app.RoomModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by lance on 18/February/2020.
 */
@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class,
        RoomModule.class,
        AppBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    // Inject view that we are standing (here is WietApplication)
    void inject(WietApplication instance);

    // Declare method to inject object through components
    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);

        @BindsInstance
        Builder context(Context context);
    }

}
