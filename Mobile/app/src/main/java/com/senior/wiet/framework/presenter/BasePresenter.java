package com.senior.wiet.framework.presenter;

import com.senior.wiet.framework.view.BaseView;

/**
 * Created by lance on 18/February/2020.
 */
public interface BasePresenter<T extends BaseView> {

    void onViewAdded(T view);

    void onViewRemoved();
}
