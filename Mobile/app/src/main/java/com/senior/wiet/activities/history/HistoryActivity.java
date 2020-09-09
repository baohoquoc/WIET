package com.senior.wiet.activities.history;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.senior.wiet.R;
import com.senior.wiet.activities.BaseActivity;
import com.senior.wiet.databinding.ActivityHistoryBinding;
import com.senior.wiet.lib.customui.AlertDialog;
import com.senior.wiet.lib.customui.ProgressBarDialog;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.history.HistoryModel;
import com.senior.wiet.lib.model.history.Values;
import com.senior.wiet.viewholder.HistoryItemAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.disposables.Disposable;

public class HistoryActivity extends BaseActivity<HistoryContract.Presenter> implements HistoryContract.View {

    @Inject
    Context mContext;

    @Inject
    AppSharedPreference provideAppSharedPreference;

    @Inject
    HistoryPresenter mHistoryPresenter;

    @Inject
    ProgressBarDialog mProgressBarDialog;

    @Inject
    @Named("vertical")
    LinearLayoutManager mLinearLayoutManager;

    public static AlertDialog mAlertDialog;

    private ActivityHistoryBinding binding;
    private Disposable mDisposable;

    public static List<Values> list = new ArrayList<>();
    public static HistoryItemAdapter historyItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history);
        super.onCreate(savedInstanceState);
        binding.setPresenter(getPresenter());
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //setContentView(R.layout.activity_history);
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.default_avatar)
                .error(R.drawable.default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(mContext).load(AppSharedPreference.getInstance(mContext).getAvatar()).apply(options).into(binding.avatar);
        historyItemAdapter = new HistoryItemAdapter(this, list,this);
        binding.viewHistory.setLayoutManager(mLinearLayoutManager);
        binding.viewHistory.setHasFixedSize(true);
        binding.viewHistory.setAdapter(historyItemAdapter);
    }

    @Override
    public void attachModel(HistoryModel historyModel) {
        binding.setModel(historyModel);
    }

    @Override
    public ActivityHistoryBinding bindView() {
        return binding;
    }


    @Override
    public void destroyAPI(Disposable disposable) {
        mDisposable = disposable;
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
    protected void onDestroy() {
        if (mDisposable != null && mDisposable.isDisposed())
            mDisposable.dispose(); // This code to destroy API if LoginActivity is destroyed
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHistoryPresenter.refresh();
    }
}
