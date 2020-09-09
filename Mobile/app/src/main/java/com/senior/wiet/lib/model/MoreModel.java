package com.senior.wiet.lib.model;

import androidx.databinding.BaseObservable;

import com.senior.wiet.fragments.more.MorePresenter;

/**
 * Created by Mitt on 21/February/2020.
 */
public class MoreModel extends BaseObservable {

    private MorePresenter morePresenter;

    public MoreModel( ) {

    }

    public void attachPresenter(MorePresenter morePresenter) {
        this.morePresenter = morePresenter;
    }
}