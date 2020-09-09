package com.senior.wiet.fragments.meal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.senior.wiet.R;
import com.senior.wiet.activities.food.FoodActivity;
import com.senior.wiet.databinding.FragmentMealBinding;
import com.senior.wiet.fragments.BaseFragment;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.MealModel;
import com.senior.wiet.lib.model.mealtoday.Meal;
import com.senior.wiet.utilities.Constants;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


public class MealFragment extends BaseFragment<MealContract.Presenter> implements MealContract.View {

    @Inject
    MealPresenter mealPresenter;

    @Inject
    MealModel mealModel;

    @Inject
    Context mContext;

    public View view;
    private AppSharedPreference mAppSharedPreference;

    private FragmentMealBinding binding;

    @Inject
    public MealFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_meal, container, false);
        binding = DataBindingUtil.bind(view);
        mealPresenter = new MealPresenter(this);
        Objects.requireNonNull(binding).setPresenter(mealPresenter);
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new CountDownTimer(1000,1000){
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        refresh();
                        binding.refreshLayout.setRefreshing(false);
                    }
                }.start();

            }
        });
        return view;
    }

//    private void getWeatherAPI() {
//        // Check condition
//        // Show dialog
//        mealPresenter.getWeatherByAPI();
//    }

    @Override
    public void attachModel(MealModel mealModel) {
        binding.setModel(mealModel);
    }

    @Override
    public FragmentMealBinding bindView() {
        return binding;
    }

    @Override
    public void destroyAPI(Disposable disposable) { }

    @Override
    public void mealFailure(String message) { }

    @Override
    public void onBreakfastClick1() {
        int Bid = AppSharedPreference.getInstance(mContext).getBid();
        Intent intent = new Intent(getContext(), FoodActivity.class);
        intent.putExtra(Constants.FOOD_ID, Bid);
        startActivity(intent);
    }

    @Override
    public void onLunchClick1() {
        int Lid = AppSharedPreference.getInstance(mContext).getLid();
        Intent intent = new Intent(getContext(), FoodActivity.class);
        intent.putExtra(Constants.FOOD_ID, Lid);
        startActivity(intent);
    }

    @Override
    public void onDinnerClick1() {
        int Did = AppSharedPreference.getInstance(mContext).getDid();
        Intent intent = new Intent(getContext(), FoodActivity.class);
        intent.putExtra(Constants.FOOD_ID, Did);
        startActivity(intent);
    }

    @Override
    public void onTemperatureClick1() {
        int Tid = AppSharedPreference.getInstance(mContext).getTid();
        Intent intent = new Intent(getContext(), FoodActivity.class);
        intent.putExtra(Constants.FOOD_ID, Tid);
        startActivity(intent);
    }

    @Override
    public void updatePlaceHolderFood(Meal meal) {
        Log.i("TAG", meal.getBreakfast().getImage());

        Glide.with(mContext)
                .load(meal.getBreakfast().getImage())
                .into(binding.imgBreakfast);

        Glide.with(mContext)
                .load(meal.getLunch().getImage())
                .into(binding.imgLunch);

        Glide.with(mContext)
                .load(meal.getDinner().getImage())
                .into(binding.imgDinner);

        Glide.with(mContext)
                .load(meal.getTemperature().getImage())
                .into(binding.imgTemperature);

    }

    @Override
    public void refresh() {
        Fragment frg = this;
        final FragmentTransaction ft =  getFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();
        //mealPresenter.refresh();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }



}
