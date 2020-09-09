package com.senior.wiet.lib.model;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.Gson;
import com.senior.wiet.R;
import com.senior.wiet.fragments.search.SearchFragment;
import com.senior.wiet.fragments.search.SearchPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.response.TopRatingResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Utilities;

/**
 * Created by Mitt on 21/February/2020.
 */
public class SearchModel extends BaseObservable {

    private String titleSearch;
    private Context mContext;
    private SearchPresenter mPresenter;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;

    public SearchModel(Context mContext, DisposableManager mDisposableManager, WietApiService mWietApiService) {
        this.mContext = mContext;
        this.mDisposableManager = mDisposableManager;
        this.mWietApiService = mWietApiService;
    }

    @Bindable
    public String getTitleSearch() {
        return titleSearch;
    }

    public void attachPresenter(SearchPresenter searchPresenter) {
        this.mPresenter = searchPresenter;
    }

    public void getTopRatingDishes(String accessToken, int top, int offset) {
        mPresenter.showProgressDialog();
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.getTopRatingDishes(mWietApiService.getTopRatingDishes("Bearer " + accessToken, top, offset), new IDisposableListener<TopRatingResponse>() {
                @Override
                public void onComplete() {
                    mPresenter.dismissProgressDialog();
                }

                @Override
                public void onHandleData(TopRatingResponse topRatingResponse) {
                    Utilities.Log("top_rating_dishes", new Gson().toJson(topRatingResponse.getValues()));
                    //SearchFragment.topRatingDishes = topRatingResponse.getValues();
                    SearchFragment.topRatingDishesAdapter.setData(topRatingResponse.getValues());
                    SearchFragment.topRatingDishesAdapter.notifyDataSetChanged();
                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    mPresenter.dismissProgressDialog();
                }

                @Override
                public void onApiFailure(Throwable error) {
                    mPresenter.dismissProgressDialog();
                }
            }));
        }
    }

    public void getTopRatingDishesMore(String accessToken, int top, int offset) {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.getTopRatingDishes(mWietApiService.getTopRatingDishes("Bearer " + accessToken, top, offset), new IDisposableListener<TopRatingResponse>() {
                @Override
                public void onComplete() {
                    mPresenter.dismissProgressDialog();
                }

                @Override
                public void onHandleData(TopRatingResponse topRatingResponse) {
                    Utilities.Log("top_rating_dishes", new Gson().toJson(topRatingResponse.getValues()));
                    SearchFragment.topRatingDishesAdapter.addData(topRatingResponse.getValues());
                    SearchFragment.topRatingDishesAdapter.notifyDataSetChanged();
                    SearchFragment.loading = true;
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

    public void search(String accessToken, String search, int limit) {
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.getTopRatingDishes(mWietApiService.searchDish("Bearer " + accessToken, search, limit), new IDisposableListener<TopRatingResponse>() {
                @Override
                public void onComplete() {
                    mPresenter.dismissProgressDialog();
                }

                @Override
                public void onHandleData(TopRatingResponse topRatingResponse) {
                    Utilities.Log("search_dishes", new Gson().toJson(topRatingResponse.getValues()));
                    SearchFragment.dishSuggestionsAdapter.setData(topRatingResponse.getValues());
                    if(topRatingResponse.getValues().isEmpty()){
                        Toast.makeText(mContext,mContext.getString(R.string.not_have_search_result),Toast.LENGTH_SHORT).show();
                    }
                    SearchFragment.dishSuggestionsAdapter.notifyDataSetChanged();
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