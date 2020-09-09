package com.senior.wiet.framework.di.binding;

import com.senior.wiet.activities.bookmark.BookmarkActivity;
import com.senior.wiet.activities.history.HistoryActivity;
import com.senior.wiet.activities.information.InformationActivity;
import com.senior.wiet.activities.login.LoginActivity;
import com.senior.wiet.activities.mainscreen.MainActivity;
import com.senior.wiet.activities.profile.ProfileActivity;
import com.senior.wiet.activities.splash.SplashActivity;
import com.senior.wiet.activities.survey.SurveyActivity;
import com.senior.wiet.framework.di.module.bookmark.BookmarkModule;
import com.senior.wiet.framework.di.module.history.HistoryModule;
import com.senior.wiet.framework.di.module.information.InformationModule;
import com.senior.wiet.framework.di.module.login.LoginModule;
import com.senior.wiet.framework.di.module.main.MainModule;
import com.senior.wiet.framework.di.module.profile.ProfileModule;
import com.senior.wiet.framework.di.module.splash.SplashModule;
import com.senior.wiet.framework.di.module.survey.SurveyModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by lance on 18/February/2020.
 */
@Module
public abstract class UserBindingModule {

    @ContributesAndroidInjector(modules = {LoginModule.class, AndroidSupportInjectionModule.class})
    abstract LoginActivity loginActivity();

    @ContributesAndroidInjector(modules = {InformationModule.class, AndroidSupportInjectionModule.class})
    abstract InformationActivity informationActivity();

    @ContributesAndroidInjector(modules = {SurveyModule.class, AndroidSupportInjectionModule.class})
    abstract SurveyActivity surveyActivity();

    @ContributesAndroidInjector(modules = {MainModule.class, AndroidSupportInjectionModule.class})
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = {BookmarkModule.class, AndroidSupportInjectionModule.class})
    abstract BookmarkActivity bookmarkActivity();

    @ContributesAndroidInjector(modules = {ProfileModule.class, AndroidSupportInjectionModule.class})
    abstract ProfileActivity profileActivity();

    @ContributesAndroidInjector(modules = {SplashModule.class, AndroidSupportInjectionModule.class})
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = {HistoryModule.class, AndroidSupportInjectionModule.class})
    abstract HistoryActivity historyActivity();
}
