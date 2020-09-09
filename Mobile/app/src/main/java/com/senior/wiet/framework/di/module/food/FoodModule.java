package com.senior.wiet.framework.di.module.food;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.senior.wiet.activities.food.FoodContract;
import com.senior.wiet.activities.food.FoodPresenter;
import com.senior.wiet.framework.di.scope.ActivityScope;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.FoodModel;
import com.senior.wiet.lib.service.WietApiService;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lance on 17/March/2020.
 */
@Module
public abstract class FoodModule {

    @Binds
    public abstract FoodContract.Presenter provideFoodPresenter(FoodPresenter presenter);

    @Provides
    @ActivityScope
    static FoodModel FoodModel(Context mContext, DisposableManager mDisposableManager, WietApiService mWietApiService, AppSharedPreference appSharedPreference) {
        return new FoodModel(mContext, mDisposableManager, mWietApiService, appSharedPreference); }

    @Provides
    @ActivityScope
    static AppSharedPreference provideAppSharedPreference(Context context) {
        return AppSharedPreference.getInstance(context);
    }

    @Provides
    @ActivityScope
    static AlertDialog alertDialog(){
        return new AlertDialog();
    }

    @Provides
    @ActivityScope
    @Named("horizontal")
    static LinearLayoutManager getLayoutManagerHorizontal(Context mContext){
        return new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
    }

}
