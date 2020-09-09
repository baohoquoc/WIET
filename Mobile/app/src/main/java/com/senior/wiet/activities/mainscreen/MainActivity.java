package com.senior.wiet.activities.mainscreen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.senior.wiet.R;
import com.senior.wiet.activities.BaseActivity;
import com.senior.wiet.databinding.ActivityMainBinding;
import com.senior.wiet.fragments.chatbox.ChatFragment;
import com.senior.wiet.fragments.meal.MealFragment;
import com.senior.wiet.fragments.more.MoreFragment;
import com.senior.wiet.fragments.recommend.RecommendFragment;
import com.senior.wiet.fragments.search.SearchFragment;
import com.senior.wiet.lib.customui.ViewPagerAdapter;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.MainModel;

import javax.inject.Inject;

/**
 * Created by lance on 18/February/2020.
 */
public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    RecommendFragment mRecommendFragmemt;

    @Inject
    MealFragment mMealFragment;

    @Inject
    SearchFragment mSearchFragment;

    @Inject
    MoreFragment mMoreFragment;

    @Inject
    ChatFragment mChatFragment;

    @Inject
    ViewPagerAdapter mViewPagerAdapter;

    private ActivityMainBinding binding;

    MenuItem prevMenuItem;

    private Activity main = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        super.onCreate(savedInstanceState);
        binding.bottomBar.setOnNavigationItemSelectedListener(this);
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.default_avatar)
                .error(R.drawable.default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(getApplicationContext()).load(AppSharedPreference.getInstance(getApplicationContext()).getAvatar()).apply(options).into(binding.imgAvatar);
        setupViewPager();
    }

    @Override
    public void attachModel(MainModel mainModel) {

    }

    @Override
    public ActivityMainBinding bindView() {
        return binding;
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

    private void setupViewPager() {
        mViewPagerAdapter.addFragment(mRecommendFragmemt);
        mViewPagerAdapter.addFragment(mMealFragment);
        mViewPagerAdapter.addFragment(mSearchFragment);
        mViewPagerAdapter.addFragment(mChatFragment);
        mViewPagerAdapter.addFragment(mMoreFragment);
        binding.viewpager.setOffscreenPageLimit(4);
        binding.viewpager.setAdapter(mViewPagerAdapter);
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                hideKeyboard(main);
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    binding.bottomBar.getMenu().getItem(0).setChecked(false);
                }
                //Log.d("page", "onPageSelected: "+position);
                binding.bottomBar.getMenu().getItem(position).setChecked(true);
                prevMenuItem = binding.bottomBar.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.recommend:
                binding.viewpager.setCurrentItem(0);
                return true;
            case R.id.meal:
                binding.viewpager.setCurrentItem(1);
                return true;
            case R.id.search:
                binding.viewpager.setCurrentItem(2);
                return true;
            case R.id.chat:
                binding.viewpager.setCurrentItem(3);
                return true;
            case R.id.more:
                binding.viewpager.setCurrentItem(4);
                return true;
        }
        return false;
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
