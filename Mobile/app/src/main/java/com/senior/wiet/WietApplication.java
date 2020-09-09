package com.senior.wiet;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import com.facebook.FacebookSdk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.senior.wiet.framework.di.component.DaggerAppDataBindingComponent;
import com.senior.wiet.framework.di.component.app.AppComponent;
import com.senior.wiet.framework.di.component.app.DaggerAppComponent;
import com.senior.wiet.framework.di.component.entities.UserManager;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.utilities.DebugConstant;
import com.senior.wiet.utilities.TypefaceUtil;
import com.senior.wiet.utilities.Utilities;

import java.security.MessageDigest;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by lance on 18/February/2020.
 */
public class WietApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
    @Inject
    UserManager userManager;

    private AppComponent appComponent;

    /*
     * This method can be used to initialize
     * and get Context of Application, and instance of Wiet Application.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf");
        FirebaseApp.initializeApp(getApplicationContext());
        FacebookSdk.sdkInitialize(getApplicationContext());
        DataBindingUtil.setDefaultComponent(buildDataBindingComponent());
        createComponent();
        printHashKey(getApplicationContext());
        printFcmToken(getApplicationContext());
    }

    protected void createComponent() {
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .context(getApplicationContext())
                .build();
        appComponent.inject(this);
    }

    /*
     * This method can be used to bind Parent Component for Activity
     * @return
     */
    private DataBindingComponent buildDataBindingComponent() {
        return DaggerAppDataBindingComponent.builder()
                .application(this)
                .build();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return userManager.activityInjector();
    }

    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Utilities.Log(DebugConstant.KEY_HASH, hashKey);
            }
        } catch (Exception e) {
            Utilities.Log(DebugConstant.Error, e.getMessage());
        }
    }

    /*
     * This method to print fcm token
     * @param pContext
     */
    public static void printFcmToken(Context pContext) {
        try {
            FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                @Override
                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                    if (!task.isSuccessful()) {
                        return;
                    }
                    // Get new Instance ID token
                    String fcmToken = task.getResult().getToken();
                    Utilities.Log(DebugConstant.FCM_TOKEN, fcmToken);
                    AppSharedPreference.getInstance(pContext).setFcmtoken(fcmToken);

                }
            });
        } catch (Exception e) {
            Utilities.Log(DebugConstant.Error, e.getMessage());
        }
    }
}
