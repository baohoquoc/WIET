package com.senior.wiet.framework.di.component.entities;

import android.os.UserManager;

import com.senior.wiet.framework.di.binding.UserBindingModule;
import com.senior.wiet.framework.di.scope.UserScope;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by lance on 18/February/2020.
 */
@UserScope
@Subcomponent(modules = {UserBindingModule.class, AndroidSupportInjectionModule.class})
public interface UserComponent {

    void inject(UserManager userManager);

    @Subcomponent.Builder
    interface Builder {

        UserComponent build();
        @BindsInstance
        Builder hello(String temp);
    }
}
