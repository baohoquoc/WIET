package com.senior.wiet.lib.model;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.senior.wiet.R;
import com.senior.wiet.activities.information.InformationPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.response.AllergyResponse;
import com.senior.wiet.lib.response.DeleteAllergyResponse;
import com.senior.wiet.lib.response.InformationResponse;
import com.senior.wiet.lib.response.MetaTagResponse;
import com.senior.wiet.lib.response.TagResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InformationModel extends BaseObservable{

    private Context mContext;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;
    private InformationPresenter mPresenter;
    private String tvDateOfBirth;
    public static final String DATE_DASH_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT = "dd/MM/yyyy";


    public InformationModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        this.mDisposableManager = disposableManager;
        this.mContext = context;
        this.mWietApiService = wietApiService;
    }

    public InformationModel() {

    }

    @Bindable
    public String getTvDateOfBirth() {
        if(AppSharedPreference.getInstance(mContext).getIsFirstLogin()){
            return "01/01/1990";
        }
        if (AppSharedPreference.getInstance(mContext).getDob() != null)
                return AppSharedPreference.getInstance(mContext).getDob();
        return tvDateOfBirth;
    }

    public void setTvDateOfBirth(String tvDateOfBirth) {
        this.tvDateOfBirth = tvDateOfBirth;
    }

    public void attachPresenter(InformationPresenter informationPresenter) {
        this.mPresenter = informationPresenter;
    }


    /**
     * This method handling data from server
     * @param uid
     * @param email
     * @param dob
     * @param fullname
     * @param isVegetarian
     * @param avatar
     */
    public void information(String uid, String email, String dob, String fullname, Boolean isVegetarian, String avatar, String location) {
        dob = prepareYearMonthDateFromString(dob);
        InformationRequest informationRequest = new InformationRequest(uid, email, dob, fullname, isVegetarian, avatar, location);

        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.information(mWietApiService.information( "Bearer " + AppSharedPreference.getInstance(mContext).getAccessToken(), informationRequest), new IDisposableListener<InformationResponse>() {

                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(InformationResponse informationResponse) {
                    mPresenter.unpdateInfoSuccess();

                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    // TODO
                }

                @Override
                public void onApiFailure(Throwable error) {
                    mPresenter.inputFailure(error.getMessage());
                }
            }));
        } else {
            mPresenter.inputFailure(mContext.getString(R.string.error_no_internet));
        }

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

    public void onSummitUserInfo(String location) {
        String uid = AppSharedPreference.getInstance(mContext).getUid();
        String email = AppSharedPreference.getInstance(mContext).getEmail();
        String dob = AppSharedPreference.getInstance(mContext).getDob();
        String fullname = AppSharedPreference.getInstance(mContext).getFullName();
        Boolean is_vegetarian = AppSharedPreference.getInstance(mContext).getIsVegetarian();
        String avatar = AppSharedPreference.getInstance(mContext).getAvatar();
        AppSharedPreference.getInstance(mContext).setUserLocation(location);
        information(uid, email, dob, fullname, is_vegetarian, avatar, location);
    }

    public void searchMetaTags(String query) {
        TagValues mTagValues = new TagValues();
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.searchMetaTags(mWietApiService.searchMetaTags( "Bearer " + AppSharedPreference.getInstance(mContext).getAccessToken(), query ,"10"), new IDisposableListener<MetaTagResponse>() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(MetaTagResponse metaTagResponse) {
                    mPresenter.tagGetSuccess(metaTagResponse.getValues());
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

    public void allergy(Allergy allergy) {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.allergy(mWietApiService.allergy("Bearer " + AppSharedPreference.getInstance(mContext).getAccessToken(), allergy), new IDisposableListener<AllergyResponse>() {

                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(AllergyResponse allergyResponse) {
                    mPresenter.inputAllergySuccess();
                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    // TODO
                }

                @Override
                public void onApiFailure(Throwable error) {
                    mPresenter.inputFailure(error.getMessage());
                }
            }));
        } else {
            mPresenter.inputFailure(mContext.getString(R.string.error_no_internet));
        }

    }

    public void deleteAllergy(Integer allergyID) {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.deleteAllergy(mWietApiService.deleteAllergy("Bearer " + AppSharedPreference.getInstance(mContext).getAccessToken(), allergyID), new IDisposableListener<DeleteAllergyResponse>() {

                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(DeleteAllergyResponse deleteAllergyResponse) {

                    mPresenter.deleteSuccess();

                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    // TODO
                }

                @Override
                public void onApiFailure(Throwable error) {
                    mPresenter.inputFailure(error.getMessage());
                }
            }));
        } else {
            mPresenter.inputFailure(mContext.getString(R.string.error_no_internet));
        }

    }
}
