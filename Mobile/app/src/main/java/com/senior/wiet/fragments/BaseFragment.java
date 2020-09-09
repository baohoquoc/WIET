package com.senior.wiet.fragments;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Created by Mitt on 3/19/2020.
 */
public class BaseFragment<T extends BasePresenter> extends DaggerFragment implements BaseView {

    public T getPresenter() {
        return presenter;
    }

    @Inject
    T presenter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onViewAdded(this);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBarDialog() {

    }

    @Override
    public void hideProgressBarDialog() {

    }

    @Override
    public void onDetach() {
        presenter.onViewRemoved();
        super.onDetach();
    }

}
