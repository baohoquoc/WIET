package com.senior.wiet.activities.bookmark;

import com.senior.wiet.databinding.ActivityBookmarkBinding;
import com.senior.wiet.framework.presenter.BasePresenter;
import com.senior.wiet.framework.view.BaseView;
import com.senior.wiet.lib.model.BookmarkModel;
import com.senior.wiet.lib.model.bookmark.BookmarkValues;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Mitt on 4/3/2020.
 */
public interface BookmarkContract {

    interface View extends BaseView {
        void attachModel(BookmarkModel bookmarkModel);

        ActivityBookmarkBinding bindView();

        void destroyAPI(Disposable disposable);
    }

    interface Presenter extends BasePresenter<BookmarkContract.View> {
        void destroyAPI(Disposable disposable);

        void getListBookmark();

        void bookmarkGetSuccess(List<BookmarkValues> bookmarkValues);

        void removeItem(int position);

        void showItem(BookmarkValues item);
    }
}
