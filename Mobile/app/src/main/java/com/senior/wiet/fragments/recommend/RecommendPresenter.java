package com.senior.wiet.fragments.recommend;

import android.content.Context;
import android.widget.Toast;

import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.RecommendModel;
import com.senior.wiet.lib.model.RecommendValues;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Create by mitt on 3/6/2020.
 */


public class RecommendPresenter extends BasePresenterImpl<RecommendContract.View> implements RecommendContract.Presenter {

    private Context mContext;
    public static RecommendModel mRecommendModel;

    RecommendContract.View view;

    RecommendFragment rf;
    public static List<RecommendValues> listItemsRecommeendResponse;
    public int indexItem = 0;

    @Inject
    public RecommendPresenter(Context context, RecommendModel recommendModel) {
        this.mContext = context;
        this.mRecommendModel = recommendModel;

    }

    public RecommendPresenter(RecommendContract.View view) {
        this.view = view;
    }

    @Override
    public void getRecommendByLocation() {
        String location = AppSharedPreference.getInstance(mContext).getUserLocation();
        //"Đà Nẵng";
        //mRecommendModel.getRecommendByLocation(AppSharedPreference.getInstance(mContext).getLocation());
        mRecommendModel.getRecommendByLocation(location);
    }

    @Override
    public void initFirstRecommend() {
        this.view.init();
    }

    @Override
    public void createHistory(int id_food) {
        mRecommendModel.createHistory(AppSharedPreference.getInstance(mContext).getAccessToken(), id_food);
    }


    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);

    }

    @Override
    public void onViewAdded(RecommendContract.View view) {
        super.onViewAdded(view);
        this.view = view;
        getView().attachModel(this.mRecommendModel);
        mRecommendModel.attachPresenter(this);
        getRecommendByLocation();
        //this.view.setList(getListItemsRecommendResponse());
        //this.view.showItemFoodByIndex();

    }

    @Override
    public void getValues(List<RecommendValues> recommendValues) {

    }

    @Override
    public void setDisposable(Disposable disposable) {

    }


    /*public  List<RecommendValues> getListItemsRecommendResponse() {
        return listItemsRecommendResponse;
    }

    public  void setListItemsRecommendResponse(List<RecommendValues> listItemsRecommendResponse) {
        this.listItemsRecommendResponse = listItemsRecommendResponse;
    }*/

    public int getIndexItem() {
        return indexItem;
    }

    public void setIndexItem(int indexItem) {
        this.indexItem = indexItem;
    }

    public void updateLocationSuccess(String location) {
        AppSharedPreference.getInstance(mContext).setUserLocation(location);
        getRecommendByLocation();
      //  Toast.makeText(mContext, location, Toast.LENGTH_LONG).show();
    }

    public void updateLocationFailure(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }
}
