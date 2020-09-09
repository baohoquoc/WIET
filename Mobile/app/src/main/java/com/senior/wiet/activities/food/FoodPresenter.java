package com.senior.wiet.activities.food;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.senior.wiet.R;
import com.senior.wiet.activities.map.MapsActivity;
import com.senior.wiet.activities.remove.RemoveVNCharacter;
import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.FoodModel;
import com.senior.wiet.lib.model.fooddetail.FoodDetailValue;
import com.senior.wiet.utilities.Constants;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by lance on 18/February/2020.
 */
public class FoodPresenter extends BasePresenterImpl<FoodContract.View> implements FoodContract.Presenter, OnCompleteListener {

    private FoodModel foodModel;
    private FoodActivity foodActivity;
    private Context mContext;
    private AlertDialog mAlertDialog;
    private AppSharedPreference mAppSharedPreference;
    private RemoveVNCharacter removeVNCharacter;

    @Inject
    public FoodPresenter(FoodActivity foodActivity, FoodModel foodModel, Context context, AppSharedPreference appSharedPreference) {
        this.foodActivity = foodActivity;
        this.foodModel = foodModel;
        this.mContext = context;
        this.mAppSharedPreference = appSharedPreference;
    }

    @Override
    public void onViewAdded(FoodContract.View view) {
        super.onViewAdded(view);
        getView().attachModel(this.foodModel);
        foodModel.attachPresenter(this);
        String accessToken = mAppSharedPreference.getAccessToken();
        int id = foodActivity.getIntent().getIntExtra(Constants.FOOD_ID, 1);
        foodModel.foodDetail(id, accessToken);
    }

    @Override
    public void onComplete(@NonNull Task task) {

    }

    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);
    }

    @Override
    public void foodSuccess(FoodDetailValue foodDetailValue) {
    }


    @Override
    public void foodFailure(String message) {
        getView().foodFailure(message);
    }

    @Override
    public void onBookmarkClick() {
        int food_id = mAppSharedPreference.getFdfoodid();
        String accessToken = AppSharedPreference.getInstance(mContext).getAccessToken();
        Boolean is_Bookmark = mAppSharedPreference.getFdisbookmark();
        if (!mAppSharedPreference.getFdisbookmark()) {
            foodModel.bookmark(food_id, accessToken);
            mAlertDialog = new AlertDialog().createDialog(mContext.getString(R.string.add_bookmark), Constants.EMPTY_STRING, "OK");
            mAlertDialog.setOnNegativeListener(new AlertDialog.OnNegativeListener() {
                @Override
                public void onNegativeListener() {
                    mAlertDialog.dismiss();
                }
            });
            mAlertDialog.show(foodActivity.getSupportFragmentManager(), "Error");
            getView().bindView().btnBookmark.setSelected(true);
            mAppSharedPreference.setFdisbookmark(true);
            //is_Bookmark = true;

        } else if (mAppSharedPreference.getFdisbookmark() == true) {
            foodModel.unbookmark(food_id, accessToken);
            mAlertDialog = new AlertDialog().createDialog(mContext.getString(R.string.remove_bookmark), Constants.EMPTY_STRING, "OK");
            mAlertDialog.setOnNegativeListener(new AlertDialog.OnNegativeListener() {
                @Override
                public void onNegativeListener() {
                    mAlertDialog.dismiss();
                }
            });
            mAlertDialog.show(foodActivity.getSupportFragmentManager(), "Error");
            getView().bindView().btnBookmark.setSelected(false);
            mAppSharedPreference.setFdisbookmark(false);
            //is_Bookmark = false;
        }
    }


    @Override
    public void onMapsClick() {
        Intent intent = new Intent(mContext, MapsActivity.class);
        foodActivity.startActivity(intent);
    }

    @Override
    public void onNowClick() {
        String nowUrl = "https://www.now.vn/";

        String finalLocation;
        String location1 = mAppSharedPreference.getFdaddress();
        String[] location2 = location1.split(", ");
        String location3 = location2[location2.length - 1];
        Log.d("CHECKLOCATION", location3);
        if (location3.equals("TP. HCM")) {
            finalLocation = "ho-chi-minh";
        } else {
            finalLocation = removeVNCharacter.removeAccent(location3.toLowerCase()).replaceAll(" ", "-");
        }

        String address1 = removeVNCharacter.removeAccent(mAppSharedPreference.getFdstorename().toLowerCase()).replaceAll(" ", "-");
        String address2 = address1.replaceAll("-&-", "-");
        String address3 = address2.replaceAll("---", "-");

        Intent i = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(nowUrl + finalLocation + "/" + address3)
        );
        foodActivity.startActivity(i);

    }

    @Override
    public void updatePlaceHolderFood(FoodDetailValue foodDetailValue) {
        getView().updatePlaceHolderFood(foodDetailValue);
    }

}
