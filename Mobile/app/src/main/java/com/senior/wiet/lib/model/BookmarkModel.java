package com.senior.wiet.lib.model;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;

import com.senior.wiet.activities.bookmark.BookmarkPresenter;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.localstorage.room.IDisposableListener;
import com.senior.wiet.lib.localstorage.sharedpreferences.AppSharedPreference;
import com.senior.wiet.lib.model.bookmark.BookmarkValues;
import com.senior.wiet.lib.response.BookmarkResponse;
import com.senior.wiet.lib.response.UnBookmarkResponse;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.utilities.Constants;
import com.senior.wiet.utilities.DebugConstant;
import com.senior.wiet.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mitt on 4/3/2020.
 */
public class BookmarkModel extends BaseObservable {

    private Context mContext;
    private DisposableManager mDisposableManager;
    private WietApiService mWietApiService;
    private BookmarkPresenter mBookmarkPresenter;

    public BookmarkModel(Context mContext, DisposableManager mDisposableManager, WietApiService mWietApiService) {
        this.mContext = mContext;
        this.mDisposableManager = mDisposableManager;
        this.mWietApiService = mWietApiService;
    }

    public BookmarkModel() {
    }

    public void attachPresenter(BookmarkPresenter bookmarkPresenter) {
        this.mBookmarkPresenter = bookmarkPresenter;
    }

    /**
     * This is method handing data from server
     */
    public void getListBookmark() {
        if (Utilities.isNetworkConnected(mContext)) {
            String token = AppSharedPreference.getInstance(mContext).getAccessToken();
                    //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODQwMTEzNDEsIm5iZiI6MTU4NDAxMTM0MSwianRpIjoiYWVlZGI1YjktNTMwZS00ZTc5LTk0MzMtMDVhZTE2Y2UzNDhhIiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.NKwfNBkwlaAK-TF9fLzG8Oizx-8ElpS9CqBHpJuENHg";
                   // "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODQwMTEzNDEsIm5iZiI6MTU4NDAxMTM0MSwianRpIjoiYWVlZGI1YjktNTMwZS00ZTc5LTk0MzMtMDVhZTE2Y2UzNDhhIiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.NKwfNBkwlaAK-TF9fLzG8Oizx-8ElpS9CqBHpJuENHg";
            mBookmarkPresenter.destroyAPI(mDisposableManager.bookmarks(mWietApiService.bookmarks(Constants.BEARER + token), new IDisposableListener<BookmarkResponse>() {
                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(BookmarkResponse bookmarkResponse) {
                    List<BookmarkValues> list = new ArrayList<>();
                    for (BookmarkValues item : bookmarkResponse.getBookmarkValues()) {
                        String name = item.getName().replaceAll("[()]", "");
                        item.setName(name);
                        //BookmarkActivity.list.add(item);
                        list.add(item);
                        Utilities.Log("Bookmark", "isbookmark: " + item.getIs_bookmarked());
                    }
                    mBookmarkPresenter.bookmarkGetSuccess(list);
                }

                @Override
                public void onRequestWrongData(int t, String message) {
                    Utilities.Log(DebugConstant.Error, message);
                }

                @Override
                public void onApiFailure(Throwable error) {
                    Utilities.Log(DebugConstant.Error, error.getMessage());
                }
            }));
        } else {
            Log.d("api", "Can't connect network");
        }
    }

    //unbookmark
    public void unbookmark(int food_id) {
        if (Utilities.isNetworkConnected(mContext)) {
            String token = AppSharedPreference.getInstance(mContext).getAccessToken();
                    //  "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODQwMTEzNDEsIm5iZiI6MTU4NDAxMTM0MSwianRpIjoiYWVlZGI1YjktNTMwZS00ZTc5LTk0MzMtMDVhZTE2Y2UzNDhhIiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.NKwfNBkwlaAK-TF9fLzG8Oizx-8ElpS9CqBHpJuENHg";
                    //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE1ODQwMTEzNDEsIm5iZiI6MTU4NDAxMTM0MSwianRpIjoiYWVlZGI1YjktNTMwZS00ZTc5LTk0MzMtMDVhZTE2Y2UzNDhhIiwiaWRlbnRpdHkiOiJCRThtNzBwaGRFZVJ0dmpOMzhiTkdFRGZoSFYyIiwiZnJlc2giOmZhbHNlLCJ0eXBlIjoiYWNjZXNzIn0.NKwfNBkwlaAK-TF9fLzG8Oizx-8ElpS9CqBHpJuENHg";
            mBookmarkPresenter.destroyAPI(mDisposableManager.unbookmark(mWietApiService.unbookmark(food_id, "Bearer " + token), new IDisposableListener<UnBookmarkResponse>() {

                @Override
                public void onComplete() {

                }

                @Override
                public void onHandleData(UnBookmarkResponse unBookmarkResponse) {

                }

                @Override
                public void onRequestWrongData(int t, String message) {

                }

                @Override
                public void onApiFailure(Throwable error) {

                }
            }));
        }
    }
}
