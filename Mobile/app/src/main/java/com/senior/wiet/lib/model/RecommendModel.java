package com.senior.wiet.lib.model;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.Gson;
import com.senior.wiet.BR;
import com.senior.wiet.R;
import com.senior.wiet.fragments.recommend.RecommendFragment;
import com.senior.wiet.fragments.recommend.RecommendPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.history.HistoryCreateRequest;
import com.senior.wiet.lib.response.HistoryCreateResponse;
import com.senior.wiet.lib.response.LastLocationResponse;
import com.senior.wiet.lib.response.RecommendResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.DebugConstant;
import com.senior.wiet.utilities.Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Create by mitt on 3/9/2020.
 */
public class RecommendModel extends BaseObservable {
    public String txtHeader;
    private Context mContext;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;
    private RecommendPresenter mRecommendPresenter;

    public RecommendModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        this.mContext = context;
        this.mDisposableManager = disposableManager;
        this.mWietApiService = wietApiService;
    }

    public void attachPresenter(RecommendPresenter recommendPresenter) {
        this.mRecommendPresenter = recommendPresenter;
    }

    public void getRecommendByLocation(String location) {
        if (Utilities.isNetworkConnected(mContext)) {
            String token = AppSharedPreference.getInstance(mContext).getAccessToken();

            mRecommendPresenter.destroyAPI(mDisposableManager.recommend(mWietApiService.recommend(Constants.BEARER + token, location), new IDisposableListener<RecommendResponse>() {
                @Override
                public void onComplete() {
                    mRecommendPresenter.initFirstRecommend();
                }

                @Override
                public void onHandleData(RecommendResponse recommendResponse) {
                    //remove parentheses () from name food
                    if (recommendResponse.isSuccess()) {
                        List<RecommendValues> listt = new ArrayList<>();
                        for (RecommendValues item : recommendResponse.getValues()) {
                            String name = item.getName().replaceAll("[()]", "");
                            item.setName(name);
                            listt.add(item);
                            Utilities.Log(DebugConstant.RECOMMEND_VALUES, name);
                        }
                        RecommendFragment.list = listt;
                        setTxt();
                    }
                    //List<RecommendValues> list = recommendResponse.getValues();

                    //mRecommendPresenter.setListItemsRecommendResponse(list);
                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    Utilities.Log(DebugConstant.Error, message);
                }

                @Override
                public void onApiFailure(Throwable error) {
                    Utilities.Log(DebugConstant.Error, error.getMessage());
                }
            }));
        } else {
            Log.d("api", "Can't connect network");
        }
    }

    public void createHistory(String accessToken, int food_id) {
        if (Utilities.isNetworkConnected(mContext)) {
            mRecommendPresenter.destroyAPI(mDisposableManager.createHistory(mWietApiService.createHistory("Bearer " + accessToken, new HistoryCreateRequest(food_id)), new IDisposableListener<HistoryCreateResponse>() {
                @Override
                public void onComplete() {
                    Utilities.Log("create_history", "done");
                }

                @Override
                public void onHandleData(HistoryCreateResponse response) {
                    Utilities.Log("history_create", new Gson().toJson(response));
                }

                @Override
                public void onRequestWrongData(int t, String message) {
                }

                @Override
                public void onApiFailure(Throwable error) {

                }
            }));

        }

    }

    public void lastLocation(String location) {
        if (Utilities.isNetworkConnected(mContext)) {
            String token = AppSharedPreference.getInstance(mContext).getAccessToken();
            Location mlocation = new Location(location);
            mRecommendPresenter.destroyAPI(mDisposableManager.lastLocation(mWietApiService.lastLocation(Constants.BEARER + token, mlocation), new IDisposableListener<LastLocationResponse>() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(LastLocationResponse lastLocationResponse) {
                    mRecommendPresenter.updateLocationSuccess(location);
                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    Utilities.Log(DebugConstant.Error, message);
                }

                @Override
                public void onApiFailure(Throwable error) {
                    mRecommendPresenter.updateLocationFailure(error.getMessage());
                    Utilities.Log(DebugConstant.Error, error.getMessage());
                }
            }));
        } else {
            Log.d("api", "Can't connect network");
        }
    }

    public void setTxt() {
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        setTxtHeader(mContext.getString(R.string.txttitilelist) + " " + date);
    }

    @Bindable
    public String getTxtHeader() {
        return txtHeader;
    }

    @Bindable
    public void setTxtHeader(String value) {
        this.txtHeader = value;
        notifyPropertyChanged(BR.txtHeader);
    }
}
