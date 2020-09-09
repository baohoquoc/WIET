package com.senior.wiet.lib.model;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.senior.wiet.R;
import com.senior.wiet.activities.food.FoodActivity;
import com.senior.wiet.activities.food.FoodPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.bookmark.FoodId;
import com.senior.wiet.lib.model.fooddetail.FoodDetailValue;
import com.senior.wiet.lib.response.BookmarkResponse;
import com.senior.wiet.lib.response.FoodDetailResponse;
import com.senior.wiet.lib.response.UnBookmarkResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Utilities;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Lance on 17/March/2020.
 */
public class FoodModel extends BaseObservable {

    private FoodPresenter mPresenter;
    private Context mContext;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;
    private AppSharedPreference mAppSharedPreference;

    public String foodName, price, distance, image, address, storeName;
    private double calculatorDistance;

    public FoodModel(Context mContext, DisposableManager mDisposableManager, WietApiService mWietApiService, AppSharedPreference appSharedPreference) {
        this.mContext = mContext;
        this.mDisposableManager = mDisposableManager;
        this.mWietApiService = mWietApiService;
        this.mAppSharedPreference = appSharedPreference;
    }

    public void foodDetail(int food_id, String accessToken){
        if(Utilities.isNetworkConnected(mContext)){
            mPresenter.destroyAPI(mDisposableManager.foodDetail(mWietApiService.foodDetail(food_id, "Bearer " + accessToken), new IDisposableListener<FoodDetailResponse>() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(FoodDetailResponse foodDetailResponse) {
                    saveFoodDetail(foodDetailResponse.getFoodDetailValue());
                    updateUI(foodDetailResponse.getFoodDetailValue());
                    mPresenter.foodSuccess(foodDetailResponse.getFoodDetailValue());
                    FoodActivity.foodItemAdapter.setData(foodDetailResponse.getFoodDetailValuesList());
                    FoodActivity.foodItemAdapter.notifyDataSetChanged();
                    Log.d("TEST", ""+foodDetailResponse.getFoodDetailValue().getId_food());
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

    public void bookmark(int food_id, String accessToken){
        FoodId foodId = new FoodId(food_id);
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.bookmark(mWietApiService.bookmark(foodId, "Bearer " + accessToken), new IDisposableListener<BookmarkResponse>() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(BookmarkResponse bookmarkResponse) {

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

    public void unbookmark(int food_id, String accessToken){
        if (Utilities.isNetworkConnected(mContext)) {
            mPresenter.destroyAPI(mDisposableManager.unbookmark(mWietApiService.unbookmark(food_id, "Bearer " + accessToken), new IDisposableListener<UnBookmarkResponse>() {

                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(UnBookmarkResponse unBookmarkResponse) {

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

    public void attachPresenter(FoodPresenter foodPresenter) {
        this.mPresenter = foodPresenter;
    }

    private void saveFoodDetail(FoodDetailValue foodDetailValue) {

        mAppSharedPreference.setFdstorename(foodDetailValue.getStore_name());
        mAppSharedPreference.setFdaddress(foodDetailValue.getAddress());
        mAppSharedPreference.setFdname(foodDetailValue.getName());
        mAppSharedPreference.setFdlat(foodDetailValue.getLatitude());
        mAppSharedPreference.setFdlon(foodDetailValue.getLongitude());
        mAppSharedPreference.setFdfoodid(foodDetailValue.getId_food());
        Log.d("TEST", ""+mAppSharedPreference.getFdfoodid());
        mAppSharedPreference.setFdisbookmark(foodDetailValue.getIs_bookmark());

        calculatorDistance();
    }

    private void updateUI(FoodDetailValue foodDetailValue) {
        setFoodName(foodDetailValue.getName().replaceAll("[()]", ""));
        int price = foodDetailValue.getPrice();
        String lang=Resources.getSystem().getConfiguration().locale.getLanguage();
        if(lang.equalsIgnoreCase("en")){
            setPrice("VND " + NumberFormat.getNumberInstance(Locale.US).format(price));
        }else {
            setPrice(NumberFormat.getNumberInstance(Locale.US).format(price)+" VNĐ");
        }
        //Log.d("Lang", lang);
        //setPrice("VND " + NumberFormat.getNumberInstance(Locale.US).format(price));
        setStoreName(foodDetailValue.getStore_name());
        setAddress(foodDetailValue.getAddress());

        setDistance(String.format("%.1f",calculatorDistance/1000) +" "+ mContext.getString(R.string.km_from_here));
        mPresenter.updatePlaceHolderFood(foodDetailValue);
    }

    public double distance(float lat1, float lon1, float lat2, float lon2) {
        double R = 6378.137;
        double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
        double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d * 1000;
    }

    public void calculatorDistance() {
        double rLat = mAppSharedPreference.getRealLat();
        double rLon = mAppSharedPreference.getRealLong();
        Log.d("LATLON", "" +rLat +"," +rLon);
        float lat = mAppSharedPreference.getFdlat();
        float lon = mAppSharedPreference.getFdlon();

        calculatorDistance = distance((float)rLat,(float)rLon, lat, lon);


    }

    @Bindable
    public String getFoodName() {
        return foodName;
    }
    @Bindable
    public void setFoodName(String value) {
        this.foodName = value;
        notifyPropertyChanged(com.senior.wiet.BR.foodName);
    }
    @Bindable
    public String getPrice() {
        return price;
    }
    @Bindable
    public void setPrice(String value) {
        this.price = value;
        notifyPropertyChanged(com.senior.wiet.BR.price);
    }
    @Bindable
    public String getStoreName() {
        return storeName;
    }
    @Bindable
    public void setStoreName(String value) {
        this.storeName = value;
        notifyPropertyChanged(com.senior.wiet.BR.storeName);
    }
    @Bindable
    public String getDistance() {
        return distance;
    }
    @Bindable
    public void setDistance(String value) {
        this.distance = value;
        notifyPropertyChanged(com.senior.wiet.BR.distance);
    }
    @Bindable
    public String getAddress() {
        return address;
    }
    @Bindable
    public void setAddress(String value) {
        this.address = value;
        notifyPropertyChanged(com.senior.wiet.BR.address);
    }
    @Bindable
    public String getImage() {
        return image;
    }
    @Bindable
    public void setImage(String value) {
        this.image = value;
        notifyPropertyChanged(com.senior.wiet.BR.image);
    }



}