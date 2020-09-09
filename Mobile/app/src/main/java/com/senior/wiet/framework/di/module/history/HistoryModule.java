package com.senior.wiet.framework.di.module.history;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.senior.wiet.activities.history.HistoryActivity;
import com.senior.wiet.activities.history.HistoryContract;
import com.senior.wiet.activities.history.HistoryPresenter;
import com.senior.wiet.framework.di.scope.ActivityScope;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.history.HistoryModel;
import com.senior.wiet.lib.service.WietApiService;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lance on 16/April/2020.
 */

@Module
public abstract class HistoryModule {

    @Binds
    public abstract HistoryContract.Presenter provideHistoryPresenter(HistoryPresenter presenter);

    @Provides
    @ActivityScope
    static HistoryModel HistoryModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        return new HistoryModel(disposableManager,context,wietApiService);
    }

    @Provides
    @ActivityScope
    static AppSharedPreference provideAppSharedPreference(Context context) {
        return AppSharedPreference.getInstance(context);
    }

    @Provides
    @ActivityScope
    static ProgressBarDialog progressBarDialog(HistoryActivity activity) {
        return new ProgressBarDialog(activity);
    }

    @Provides
    @ActivityScope
    static AlertDialog alertDialog(){
        return new AlertDialog();
    }

    @Provides
    @ActivityScope
    @Named("vertical")
    static LinearLayoutManager getLayoutManagerVertical(Context mContext){
        return new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
    }
}
