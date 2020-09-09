package com.senior.wiet.framework.di.module;

import android.app.Application;

import androidx.room.Room;

import com.senior.wiet.lib.localstorage.room.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lance on 18/February/2020.
 */
@Module
public class AppDataBindingModule {

    @Provides
    @Singleton
    static AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, AppDatabase.DB_NAME).build();
    }
}
