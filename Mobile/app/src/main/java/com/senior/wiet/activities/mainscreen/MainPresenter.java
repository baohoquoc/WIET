package com.senior.wiet.activities.mainscreen;

import com.senior.wiet.framework.presenter.BasePresenterImpl;

import javax.inject.Inject;

/**
 * Created by Mitt on 19/February/2020.
 */
public class MainPresenter extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainPresenter() {
    }
}
