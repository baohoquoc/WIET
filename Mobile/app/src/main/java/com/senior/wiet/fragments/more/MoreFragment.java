package com.senior.wiet.fragments.more;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.senior.wiet.R;
import com.senior.wiet.activities.bookmark.BookmarkActivity;
import com.senior.wiet.activities.history.HistoryActivity;
import com.senior.wiet.activities.mainscreen.MainActivity;
import com.senior.wiet.activities.profile.ProfileActivity;
import com.senior.wiet.activities.splash.SplashActivity;
import com.senior.wiet.databinding.FragmentMoreBinding;
import com.senior.wiet.fragments.BaseFragment;
import com.senior.wiet.lib.model.MoreModel;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


public class MoreFragment extends BaseFragment<MoreContract.Presenter> implements MoreContract.View {

    private FragmentMoreBinding binding;

    @Inject
    MainActivity mActivity;

    @Inject
    MorePresenter mPresenter;

    @Inject
    public MoreFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        binding = DataBindingUtil.bind(view);
        mPresenter.onViewAdded(this);
        binding.setPresenter(mPresenter);
        return view;
    }

    @Override
    public void attachModel(MoreModel moreModel) {
        binding.setModel(moreModel);
    }

    @Override
    public FragmentMoreBinding bindView() {
        return binding;
    }

    @Override
    public void destroyAPI(Disposable disposable) {

    }

    public void navigate(int i) {
        Intent intent;
        if (i == 1) {
            //go to "lich su mon an"
            intent = new Intent(mActivity, HistoryActivity.class);
            startActivity(intent);
        } else if (i == 2) {
            //go to "Mon an da luu"
            intent = new Intent(mActivity, BookmarkActivity.class);
            startActivity(intent);
        } else {
            //go to "Trang ca nhan"
            intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
        }
    }

    public void logout() {
        Intent intent = new Intent(mActivity, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        mActivity.startActivity(intent);
        mActivity.finish();
    }
}
