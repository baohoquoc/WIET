package com.senior.wiet.framework.di.module.survey;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.senior.wiet.activities.survey.SurveyActivity;
import com.senior.wiet.activities.survey.SurveyContract;
import com.senior.wiet.activities.survey.SurveyPresenter;
import com.senior.wiet.framework.di.scope.ActivityScope;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.DummyJson;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.DishItem;
import com.senior.wiet.lib.model.SurveyItem;
import com.senior.wiet.lib.model.SurveyModel;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.viewholder.MetaTagItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by lance on 18/February/2020.
 */
@Module
public abstract class SurveyModule {

    @Binds
    public abstract SurveyContract.Presenter provideSurveyPresenter(SurveyPresenter presenter);

    @Provides
    @ActivityScope
    static SurveyModel SurveyModel(DisposableManager disposableManager, Context context, WietApiService wietApiService, DummyJson dummyJson) {
        return new SurveyModel(disposableManager,context,wietApiService,dummyJson);
    }

    @Provides
    @ActivityScope
    static AppSharedPreference provideAppSharedPreference(Context context) {
        return AppSharedPreference.getInstance(context);
    }

    @Provides
    @ActivityScope
    @Named("vertical")
    static LinearLayoutManager getLayoutManagerVertical(Context mContext){
        return new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
    }

    @Provides
    @ActivityScope
    @Named("horizontal")
    static LinearLayoutManager getLayoutManagerHorizontal(Context mContext){
        return new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
    }

    /**
     * get adapter for RecyclerView in Survey Screen
     * @param mContext
     * @return SurveyItemAdapter
     */
    @Provides
    @ActivityScope
    static MetaTagItemAdapter getSurveyItemAdapter(Context mContext){
        return new MetaTagItemAdapter(mContext, new ArrayList<>());
    }

    @Provides
    @ActivityScope
    static ProgressBarDialog progressBarDialog(SurveyActivity activity) {
        return new ProgressBarDialog(activity);
    }

    @Provides
    @ActivityScope
    static AlertDialog alertDialog(){
        return new AlertDialog();
    }

    @Provides
    @ActivityScope
    static List<DishItem> suggestions() { return new ArrayList<>();};

    @Provides
    @ActivityScope
    static List<SurveyItem> list(){
        return  new ArrayList<>();
    }


}
