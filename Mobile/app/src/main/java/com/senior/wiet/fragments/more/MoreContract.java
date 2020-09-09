package com.senior.wiet.fragments.more;

import com.senior.wiet.databinding.FragmentMoreBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.MoreModel;

import io.reactivex.disposables.Disposable;

/**
 * Create by mitt on 3/6/2020.
 */
public interface MoreContract {

    interface View extends BaseView {
        void attachModel(MoreModel moreModel);

        FragmentMoreBinding bindView();

        void destroyAPI(Disposable disposable);

        void navigate(int i);

        void logout();
    }

    interface Presenter extends BasePresenter<View> {
        void destroyAPI(Disposable disposable);

        void onHistoryClick();
        
        void onBookmarkClick();
        
        void onProfileClick();
        
        void onLogoutClick();
    }
}
