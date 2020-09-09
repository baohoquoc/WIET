package com.senior.wiet.activities.food;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.senior.wiet.R;
import com.senior.wiet.activities.BaseActivity;
import com.senior.wiet.databinding.ActivityFoodBinding;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.FoodModel;
import com.senior.wiet.lib.model.fooddetail.FoodDetailValue;
import com.senior.wiet.lib.model.fooddetail.FoodDetailValues;
import com.senior.wiet.viewholder.FoodItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.Disposable;

public class FoodActivity extends BaseActivity<FoodContract.Presenter> implements FoodContract.View {

    @Inject
    Context context;

    @Inject
    FoodPresenter mPresenter;

    @Inject
    @Named("horizontal")
    LinearLayoutManager mLinearLayoutManager;

    private Disposable mDisposable;
    private AppSharedPreference appSharedPreference;
    private ActivityFoodBinding binding;
    public static List<FoodDetailValues> list = new ArrayList<>();
    public static FoodItemAdapter foodItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food);
        super.onCreate(savedInstanceState);
        binding.setPresenter(getPresenter());
        initListRecommend();
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        int id = getIntent().getIntExtra(Constants.FOOD_ID, 1);
//        Log.d("INTENT", ""+id);
    }

    @Override
    public void attachModel(FoodModel foodModel) {
        binding.setModel(foodModel);
    }

    @Override
    public ActivityFoodBinding bindView() {
        return binding;
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void foodFailure(String message) {

    }

    @Override
    public void updatePlaceHolderFood(FoodDetailValue foodDetailValue) {
        Glide.with(context)
                .load(foodDetailValue.getImage())
                .into(binding.imgFood);
        binding.btnBookmark.setSelected(foodDetailValue.getIs_bookmark());
    }

    @Override
    public void initListRecommend() {
        foodItemAdapter = new FoodItemAdapter(context,list);
        binding.listRecommend.setHasFixedSize(true);
        binding.listRecommend.setLayoutManager(mLinearLayoutManager);
        binding.listRecommend.setAdapter(foodItemAdapter);
    }

    @Override
    protected void onDestroy() {
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose();
        super.onDestroy();
    }


    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProgressBarDialog() {

    }

    @Override
    public void hideProgressBarDialog() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
