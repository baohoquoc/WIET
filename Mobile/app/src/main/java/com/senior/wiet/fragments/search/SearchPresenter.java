package com.senior.wiet.fragments.search;

import android.content.Context;

import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.SearchModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Create by mitt on 3/6/2020.
 */


public class SearchPresenter extends BasePresenterImpl<SearchContract.View> implements SearchContract.Presenter {

    private SearchModel mSearchModel;
    private SearchFragment mSearchFragment;
    private Context mContext;
    private Disposable mDisposable;
    private ProgressBarDialog mProgressBarDialog;
    SearchContract.View view;
    SearchFragment searchFragment;
    //String accessToken = AppSharedPreference.getInstance(mContext).getAccessToken();
    //String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODQwMTEzNDEsIm5iZiI6MTU4NDAxMTM0MSwianRpIjoiYWVlZGI1YjktNTMwZS00ZTc5LTk0MzMtMDVhZTE2Y2UzNDhhIiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.NKwfNBkwlaAK-TF9fLzG8Oizx-8ElpS9CqBHpJuENHg";


    @Inject
    public SearchPresenter(SearchModel searchModel, ProgressBarDialog progressBarDialog, Context context) {
        this.mSearchModel = searchModel;
        this.mContext = context;
        this.mProgressBarDialog = progressBarDialog;
    }

    @Override
    public void onViewAdded(SearchContract.View view) {
        super.onViewAdded(view);
        this.view = view;
        getView().attachModel(mSearchModel);
        mSearchModel.attachPresenter(this);
        view.initView();
        mSearchModel.getTopRatingDishes(AppSharedPreference.getInstance(mContext).getAccessToken(), 10, 1);
        view.initSearchBar();

    }

    @Override
    public void destroyAPI(Disposable disposable) {
        this.mDisposable = disposable;
    }

    @Override
    public void onSearch(String query) {
        mSearchModel.search(AppSharedPreference.getInstance(mContext).getAccessToken(), query, 5);
    }

    @Override
    public void loadOffset(int offset) {
        //load more 5 dishes
        mSearchModel.getTopRatingDishesMore(AppSharedPreference.getInstance(mContext).getAccessToken(), 10, offset);
    }

    public void showProgressDialog() {
        mProgressBarDialog.show();
    }

    public void dismissProgressDialog() {
        mProgressBarDialog.dismiss();
    }
}
