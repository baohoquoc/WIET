package com.senior.wiet.framework.di.module.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senior.wiet.BuildConfig;
import com.senior.wiet.lib.service.TemperatureService;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.TrustHtppS;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lance on 18/February/2020.
 */
@Module
public class ApiModule {

    @Singleton
    @Provides
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor();
    }

    @Singleton
    @Provides
    @Named("ok-1")
    static OkHttpClient.Builder provideOkHttpClient1(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
    }

    @Singleton
    @Provides
    @Named("ok-2")
    static OkHttpClient.Builder provideOkHttpClient2(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
    }

    @Singleton
    @Provides
    static TrustHtppS provideTrustHtppS(@Named("ok-1") OkHttpClient.Builder client) {
        return new TrustHtppS(client);
    }

    @Singleton
    @Provides
    @Named("main-url")
    static String provideBaseURL1() {
        return BuildConfig.BASE_URL;
    }

    @Singleton
    @Provides
    @Named("temperature-url")
    static String provideBaseURL2() {
        return BuildConfig.TEMPERATURE_URL;
    }

    @Singleton
    @Provides
    static Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Singleton
    @Provides
    @Named("main-retrofit")
    static Retrofit provideRetrofit1(TrustHtppS trustHtppS, @Named("ok-1") OkHttpClient.Builder client, @Named("main-url") String baseUrl, Gson gson) {
        trustHtppS.intializeCertificate();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    @Named("temperature-retrofit")
    static Retrofit provideRetrofit2(TrustHtppS trustHtppS, @Named("ok-1") OkHttpClient.Builder client, @Named("temperature-url") String baseUrl, Gson gson) {
        trustHtppS.intializeCertificate();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static WietApiService provideWietService(@Named("main-retrofit") Retrofit retrofit) {
        return retrofit.create(WietApiService.class);
    }

    @Provides
    @Singleton
    static TemperatureService provideTemperatureApiService(@Named("temperature-retrofit") Retrofit retrofit){
        return retrofit.create(TemperatureService.class);
    }

}
