package com.senior.wiet.activities.history;

import android.content.Context;

import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.history.HistoryModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class HistoryPresenter extends BasePresenterImpl<HistoryContract.View> implements HistoryContract.Presenter {

    public static HistoryModel mHistoryModel;
    private HistoryActivity mHistoryActivity;
    private Disposable mDisposable;
    private ProgressBarDialog mProgressBarDialog;
    private Context mContext;
    private AlertDialog mAlertDialog;
    //private String token = AppSharedPreference.getInstance(mContext).getAccessToken();
    //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODYyNTI0ODksIm5iZiI6MTU4NjI1MjQ4OSwianRpIjoiYzgzMGIyN2QtYTMxYy00NDgzLTkzYzMtYjkzNjlmZmU3NjM0IiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.wbDFEUPcGKsVh6dZpOUUxOoFQYjgVipOh9Yi0GsiBBQ";

    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);
    }

    @Override
    public void deleteHistoryItem(int food_id) {
        mHistoryModel.deleteHistory(AppSharedPreference.getInstance(mContext).getAccessToken(), food_id);
    }

    @Override
    public void refresh() {
        mHistoryModel.getListHistory(AppSharedPreference.getInstance(mContext).getAccessToken());
    }

    @Inject
    public HistoryPresenter(HistoryActivity historyActivity, HistoryModel historyModel, ProgressBarDialog progressBarDialog, AlertDialog alertDialog, Context context) {
        this.mHistoryActivity = historyActivity;
        this.mHistoryModel = historyModel;
        this.mContext = context;
        this.mProgressBarDialog = progressBarDialog;
        mAlertDialog = alertDialog;
    }

    @Override
    public void onViewAdded(HistoryContract.View view) {
        super.onViewAdded(view);
        getView().attachModel(mHistoryModel);
        mHistoryModel.attachPresenter(this);
        refresh();
        //mHistoryModel.getListHistory(AppSharedPreference.getInstance(mContext).getAccessToken());
        //Utilities.Log("history","start");
    }

    public void showProgressDialog() {
        mProgressBarDialog.show();
    }

    public void dismissProgressDialog() {
        mProgressBarDialog.dismiss();
    }


}
