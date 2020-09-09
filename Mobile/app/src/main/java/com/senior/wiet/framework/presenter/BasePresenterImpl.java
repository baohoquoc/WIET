package com.senior.wiet.framework.presenter;

import com.senior.wiet.framework.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lance on 18/February/2020.
 */
public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public T getView() {
        return view;
    }

    private T view;

    @Override
    public void onViewAdded(T view) {
        this.view = view;
    }

    @Override
    public void onViewRemoved() {
        compositeDisposable.clear();
        view = null;
    }
}
