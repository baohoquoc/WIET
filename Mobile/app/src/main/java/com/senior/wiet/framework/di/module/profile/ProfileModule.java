package com.senior.wiet.framework.di.module.profile;

import android.content.Context;

import com.senior.wiet.activities.profile.ProfileActivity;
import com.senior.wiet.activities.profile.ProfileContract;
import com.senior.wiet.activities.profile.ProfilePresenter;
import com.senior.wiet.framework.di.scope.ActivityScope;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.ProfileModel;
import com.senior.wiet.lib.model.profile.GetAllergyValues;
import com.senior.wiet.lib.service.WietApiService;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ProfileModule {

    @Binds
    public abstract ProfileContract.Presenter provideProfilePresenter(ProfilePresenter profilePresenter);

    @Provides
    @ActivityScope
    static ProfileModel profileModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        return new ProfileModel(disposableManager, context, wietApiService);
    }

    @Provides
    @ActivityScope
    static AppSharedPreference provideAppSharedPreference(Context context) {
        return AppSharedPreference.getInstance(context);
    }

    @Provides
    @ActivityScope
    static ProgressBarDialog progressBarDialog(ProfileActivity activity) {
        return new ProgressBarDialog(activity);
    }

    @Provides
    @ActivityScope
    static List<String> getListTags(){
        return new ArrayList<>();
    }

    @Provides
    @ActivityScope
    static List<GetAllergyValues> getListAllergyTags(){
        return new ArrayList<>();
    }
}
