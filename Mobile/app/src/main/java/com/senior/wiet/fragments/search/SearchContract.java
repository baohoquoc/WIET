package com.senior.wiet.fragments.search;

import com.senior.wiet.databinding.FragmentSearchBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.SearchModel;

import io.reactivex.disposables.Disposable;

/**
 * Create by mitt on 3/6/2020.
 */
public interface SearchContract {

    interface View extends BaseView {
        void attachModel(SearchModel searchModel);

        FragmentSearchBinding bindView();

        void destroyAPI(Disposable disposable);

        void initView();

        void initSearchBar();

    }

    interface Presenter extends BasePresenter<View> {
        void destroyAPI(Disposable disposable);
        void onSearch(String query);
        void loadOffset(int offset);
    }
}
