package com.senior.wiet.framework.di.module.app;

import android.content.Context;

import com.senior.wiet.lib.localstorage.room.AppDatabase;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.DummyJson;
import com.senior.wiet.lib.localstorage.room.dao.UserDAO;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lance on 18/February/2020.
 */
@Module
public class RoomModule {

    @Singleton
    @Provides
    DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }


    @Singleton
    @Provides
    UserDAO provideUserDAO(AppDatabase appDatabase) {
        return appDatabase.getCategoryDAO();
    }

    @Singleton
    @Provides
    DummyJson provideDummyJson(Context context){
        return new DummyJson(context);
    }

}
