package com.senior.wiet.lib.model.history;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.Gson;
import com.senior.wiet.activities.history.HistoryActivity;
import com.senior.wiet.activities.history.HistoryPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.response.HistoryDeleteResponse;
import com.senior.wiet.lib.response.HistoryGetListResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Utilities;

import java.util.Collections;
import java.util.List;

public class HistoryModel extends BaseObservable {

    private String titleHistory;
    private Context mContext;
    private HistoryPresenter mPresenter;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;

    @Bindable
    public String getTitleHistory() {
        return titleHistory;
    }

    public HistoryModel(DisposableManager disposableManager, Context context, WietApiService wietApiService) {
        mDisposableManager = disposableManager;
        mContext = context;
        mWietApiService = wietApiService;
    }

    public void attachPresenter(HistoryPresenter historyPresenter) {
        this.mPresenter = historyPresenter;
    }

    public void getListHistory(String accessToken) {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.getListHistory(mWietApiService.getListHistory("Bearer " + accessToken), new IDisposableListener<HistoryGetListResponse>() {
                @Override
                public void onComplete() {
                    Utilities.Log("download_history_screen", "done");
                }

                @Override
                public void onHandleData(HistoryGetListResponse response) {
                    Utilities.Log("history_data", new Gson().toJson(response.getValues()));
                    List<Values> list = response.getValues();
                    Collections.reverse(list);     //reverse list history
                    HistoryActivity.historyItemAdapter.setData(list);
                    HistoryActivity.historyItemAdapter.notifyDataSetChanged();
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

    public void deleteHistory(String accessToken, int history_id) {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.deleteHistory(mWietApiService.deleteHistory("Bearer " + accessToken, history_id), new IDisposableListener<HistoryDeleteResponse>() {
                @Override
                public void onComplete() {
                    Utilities.Log("download_history_screen", "done");
                }

                @Override
                public void onHandleData(HistoryDeleteResponse response) {
                    Utilities.Log("history_delete_result", new Gson().toJson(response.getValues()));
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

}
