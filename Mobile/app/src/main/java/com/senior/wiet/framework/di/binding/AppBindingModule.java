package com.senior.wiet.framework.di.binding;

import com.senior.wiet.activities.bookmark.BookmarkActivity;
import com.senior.wiet.activities.food.FoodActivity;
import com.senior.wiet.activities.history.HistoryActivity;
import com.senior.wiet.activities.information.InformationActivity;
import com.senior.wiet.activities.login.LoginActivity;
import com.senior.wiet.activities.mainscreen.MainActivity;
import com.senior.wiet.activities.profile.ProfileActivity;
import com.senior.wiet.activities.splash.SplashActivity;
import com.senior.wiet.activities.survey.SurveyActivity;
import com.senior.wiet.framework.di.component.entities.UserComponent;
import com.senior.wiet.framework.di.module.bookmark.BookmarkModule;
import com.senior.wiet.framework.di.module.food.FoodModule;
import com.senior.wiet.framework.di.module.history.HistoryModule;
import com.senior.wiet.framework.di.module.information.InformationModule;
import com.senior.wiet.framework.di.module.login.LoginModule;
import com.senior.wiet.framework.di.module.main.MainModule;
import com.senior.wiet.framework.di.module.profile.ProfileModule;
import com.senior.wiet.framework.di.module.splash.SplashModule;
import com.senior.wiet.framework.di.module.survey.SurveyModule;
import com.senior.wiet.framework.di.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by lance on 18/February/2020.
 */
@Module(subcomponents = UserComponent.class)
public abstract class AppBindingModule {

    @ContributesAndroidInjector(modules = LoginModule.class)
    @ActivityScope
    abstract LoginActivity loginActivity();

    @ContributesAndroidInjector(modules = SurveyModule.class)
    @ActivityScope
    abstract SurveyActivity surveyActivity();

    @ContributesAndroidInjector(modules = InformationModule.class)
    @ActivityScope
    abstract InformationActivity informationActivity();

    @ContributesAndroidInjector(modules = MainModule.class)
    @ActivityScope
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = FoodModule.class)
    @ActivityScope
    abstract FoodActivity foodActivity();


    @ContributesAndroidInjector(modules = BookmarkModule.class)
    @ActivityScope
    abstract BookmarkActivity bookmarkActivity();

    @ContributesAndroidInjector(modules = ProfileModule.class)
    @ActivityScope
    abstract ProfileActivity profileActivity();

    @ContributesAndroidInjector(modules = SplashModule.class)
    @ActivityScope
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = HistoryModule.class)
    @ActivityScope
    abstract HistoryActivity historyActivity();


}

