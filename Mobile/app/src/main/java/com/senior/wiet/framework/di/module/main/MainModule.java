package com.senior.wiet.framework.di.module.main;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.senior.wiet.R;
import com.senior.wiet.activities.mainscreen.MainActivity;
import com.senior.wiet.activities.mainscreen.MainContract;
import com.senior.wiet.activities.mainscreen.MainPresenter;
import com.senior.wiet.fragments.chatbox.ChatContract;
import com.senior.wiet.fragments.chatbox.ChatFragment;
import com.senior.wiet.fragments.chatbox.ChatPresenter;
import com.senior.wiet.fragments.meal.MealContract;
import com.senior.wiet.fragments.meal.MealFragment;
import com.senior.wiet.fragments.meal.MealPresenter;
import com.senior.wiet.fragments.more.MoreContract;
import com.senior.wiet.fragments.more.MoreFragment;
import com.senior.wiet.fragments.more.MorePresenter;
import com.senior.wiet.fragments.recommend.RecommendContract;
import com.senior.wiet.fragments.recommend.RecommendFragment;
import com.senior.wiet.fragments.recommend.RecommendPresenter;
import com.senior.wiet.fragments.search.SearchContract;
import com.senior.wiet.fragments.search.SearchFragment;
import com.senior.wiet.fragments.search.SearchPresenter;
import com.senior.wiet.framework.di.scope.ActivityScope;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.customui.ViewPagerAdapter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.ChatModel;
import com.senior.wiet.lib.model.MealModel;
import com.senior.wiet.lib.model.MoreModel;
import com.senior.wiet.lib.model.RecommendModel;
import com.senior.wiet.lib.model.SearchModel;
import com.senior.wiet.lib.service.TemperatureService;
import com.senior.wiet.lib.service.WietApiService;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by lance on 18/February/2020.
 */
@Module
public abstract class MainModule {

    @ContributesAndroidInjector
    abstract RecommendFragment recommendFragment();

    @ContributesAndroidInjector
    abstract MoreFragment moreFragment();

    @ContributesAndroidInjector
    abstract MealFragment mealFragment();

    @ContributesAndroidInjector
    abstract SearchFragment searchFragment();

    @ContributesAndroidInjector
    abstract ChatFragment chatFragment();

    @Binds
    public abstract MainContract.Presenter provideMainPresenter(MainPresenter presenter);

    @Binds
    public abstract RecommendContract.Presenter provideRecommendPresenter(RecommendPresenter presenter);

    @Binds
    public abstract SearchContract.Presenter provideSearchPresenter(SearchPresenter presenter);

    @Binds
    public abstract MealContract.Presenter provideMealPresenter(MealPresenter presenter);

    @Binds
    public abstract MoreContract.Presenter provideMorePresenter(MorePresenter presenter);

    @Binds
    public abstract ChatContract.Presenter providerChatPresenter(ChatPresenter presenter);

    @Provides
    @ActivityScope
    static FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }


    @Provides
    @ActivityScope
    static AppSharedPreference provideAppSharedPreference(Context context) {
        return AppSharedPreference.getInstance(context);
    }

    @Provides
    @ActivityScope
    static RecommendModel recommendModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        return new RecommendModel(disposableManager, context, wietApiService);
    }

    @Provides
    @ActivityScope
    static MealModel mealModel(DisposableManager disposableManager, Context context, TemperatureService temperatureService, WietApiService wietApiService, AppSharedPreference appSharedPreference) {
        return new MealModel(disposableManager, context, temperatureService, wietApiService, appSharedPreference);
    }


    @Provides
    @ActivityScope
    static SearchModel searchModel(Context context, DisposableManager disposableManager, WietApiService wietApiService) {
        return new SearchModel(context, disposableManager, wietApiService);
    }


    @Provides
    @ActivityScope
    static MoreModel MoreModel() {
        return new MoreModel();
    }

    @Provides
    @ActivityScope
    static ChatModel chatModel() {
        return new ChatModel();
    }

    @Provides
    @ActivityScope
    static ViewPagerAdapter provideViewPagerAdapter(MainActivity activity) {
        return new ViewPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    @ActivityScope
    static ProgressBarDialog progressBarDialog(MainActivity activity) {
        return new ProgressBarDialog(activity);
    }

    @Provides
    @ActivityScope
    @Named("vertical")
    static LinearLayoutManager getLayoutManagerVertical(Context mContext) {
        return new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @ActivityScope
    @Named("horizontal")
    static LinearLayoutManager getLayoutManagerHorizontal(Context mContext) {
        return new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
    }

    //use to remove google user when logout
    @Provides
    @ActivityScope
    static GoogleSignInOptions provideGoogleSignInOptions(Context context) {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }
}
