package com.senior.wiet.activities.bookmark;

import android.content.Context;

import com.senior.wiet.framework.presenter.BasePresenterImpl;
import com.senior.wiet.lib.model.BookmarkModel;
import com.senior.wiet.lib.model.bookmark.BookmarkValues;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Mitt on 4/3/2020.
 */
public class BookmarkPresenter extends BasePresenterImpl<BookmarkContract.View> implements BookmarkContract.Presenter {

    private Context mContext;
    private BookmarkModel mBookmarkModel;
    private BookmarkActivity mBookmarkActivity;

    @Inject
    public BookmarkPresenter(Context mContext, BookmarkModel mBookmarkModel, BookmarkActivity bookmarkActivity) {
        this.mContext = mContext;
        this.mBookmarkModel = mBookmarkModel;
        this.mBookmarkActivity = bookmarkActivity;
    }


    @Override
    public void onViewAdded(BookmarkContract.View view) {
        super.onViewAdded(view);
        getView().attachModel(this.mBookmarkModel);
        mBookmarkModel.attachPresenter(this);
        getListBookmark();
    }

    @Override
    public void destroyAPI(Disposable disposable) {
        getView().destroyAPI(disposable);
    }

    @Override
    public void getListBookmark() {
        mBookmarkModel.getListBookmark();
    }

    @Override
    public void bookmarkGetSuccess(List<BookmarkValues> bookmarkValues) {
        mBookmarkActivity.setBookmarkRecyclerview(bookmarkValues);
    }

    @Override
    public void removeItem(int id) {
        //mBookmarkActivity.mBookmarkAdapter.remoteItem(position);
        mBookmarkModel.unbookmark(id);
    }

    @Override
    public void showItem(BookmarkValues item) {
        mBookmarkActivity.mBookmarkAdapter.showItem(item);
    }


}
