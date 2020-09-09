package com.senior.wiet.framework.di.module.information;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.senior.wiet.activities.information.InformationActivity;
import com.senior.wiet.activities.information.InformationContract;
import com.senior.wiet.activities.information.InformationPresenter;
import com.senior.wiet.framework.di.scope.ActivityScope;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.InformationModel;
import com.senior.wiet.lib.model.MetaTagValues;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.viewholder.TagItemAdapter;

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
public abstract class InformationModule {

    //fix
    @Binds
    public abstract InformationContract.Presenter provideInformationPresenter(InformationPresenter presenter);

    //fix
    @Provides
    @ActivityScope
    static InformationModel InformationModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        return new InformationModel(disposableManager, context, wietApiService);
    }

    @Provides
    @ActivityScope
    static AppSharedPreference provideAppSharedPreference(Context context) {
        return AppSharedPreference.getInstance(context);
    }

//    /*
//     * This method to create a ProgressBarDialog while system progress
//     * @param activity
//     * @return ProgressBarDialog
//     */
    @Provides
    @ActivityScope
    static ProgressBarDialog progressBarDialog(InformationActivity activity) {
        return new ProgressBarDialog(activity);
    }

    @Provides
    @ActivityScope
    @Named("horizontal")
    static LinearLayoutManager getLayoutManagerHorizontal(Context mContext){
        return new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
    }

    @Provides
    @ActivityScope
    @Named("vertical")
    static LinearLayoutManager getLayoutManagerVertical(Context mContext){
        return new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
    }

    /*
     * get adapter for RecyclerView in Tag search
     * @param mContext
     * @return TagItemAdapter
     */
    @Provides
    @ActivityScope
    static TagItemAdapter getTagItemAdapter(Context mContext){
        return new TagItemAdapter(mContext, new ArrayList<>());
    }

    @Provides
    @ActivityScope
    static List<String> getListTags(){
        return new ArrayList<>();
    }

    @Provides
    @ActivityScope
    static List<MetaTagValues> getListAllergyTags(){
        return new ArrayList<>();
    }
}
