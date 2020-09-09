package com.senior.wiet.lib.model;

import android.content.Context;

import androidx.databinding.BaseObservable;

import com.senior.wiet.R;
import com.senior.wiet.activities.profile.ProfilePresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.response.GetAllergyResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProfileModel extends BaseObservable {
    private Context mContext;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;
    private ProfilePresenter mPresenter;
    public static final String DATE_DASH_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public ProfileModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        this.mDisposableManager = disposableManager;
        this.mContext = context;
        this.mWietApiService = wietApiService;
    }

    public ProfileModel() {

    }

    public void attachPresenter(ProfilePresenter profilePresenter) {
        this.mPresenter = profilePresenter;
    }


    public static String prepareYearMonthDateFromString( String dateStr ){
        try
        {
            Date date = new SimpleDateFormat( DATE_FORMAT , Locale.ENGLISH ).parse( dateStr );
            DateFormat formatter = new SimpleDateFormat( DATE_DASH_FORMAT , Locale.getDefault() );
            dateStr = formatter.format( date.getTime() );
        }
        catch( ParseException e )
        {
            e.printStackTrace();
        }

        return dateStr;
    }

    public void getAllergyTags() {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.getAllergy(mWietApiService.getAllergy("Bearer " + AppSharedPreference.getInstance(mContext).getAccessToken()), new IDisposableListener<GetAllergyResponse>() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(GetAllergyResponse getAllergyResponse) {
                    mPresenter.allergyGetSuccess(getAllergyResponse.getValues());
                }

                @Override
                public void onRequestWrongData(int t, String message) {

                }

                @Override
                public void onApiFailure(Throwable error) {
                    mPresenter.inputFailure(error.getMessage());
                }
            }));
        }else{
            mPresenter.inputFailure(mContext.getString(R.string.error_no_internet));
        }
    }

}
