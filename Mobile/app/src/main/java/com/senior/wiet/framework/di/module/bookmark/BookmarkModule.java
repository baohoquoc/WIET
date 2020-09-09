package com.senior.wiet.framework.di.module.bookmark;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.senior.wiet.activities.bookmark.BookmarkActivity;
import com.senior.wiet.activities.bookmark.BookmarkContract;
import com.senior.wiet.activities.bookmark.BookmarkPresenter;
import com.senior.wiet.framework.di.scope.ActivityScope;
import com.senior.wiet.lib.localstorage.room.DisposableManager;
import com.senior.wiet.lib.model.BookmarkModel;
import com.senior.wiet.lib.model.bookmark.BookmarkValues;
import com.senior.wiet.lib.service.WietApiService;
import com.senior.wiet.viewholder.BookmarkAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Mitt on 4/3/2020.
 */
@Module
public abstract class BookmarkModule {

    @Binds
    public abstract BookmarkContract.Presenter provideBookmarkPresenter(BookmarkPresenter presenter);

    @Provides
    @ActivityScope
    static BookmarkModel BookmarkModel(Context context, DisposableManager disposableManager, WietApiService weWietApiService){
        return new BookmarkModel(context, disposableManager, weWietApiService);
    }

    @Provides
    @ActivityScope
    @Named("vertical")
    static LinearLayoutManager getLayoutManagerVertical(Context mContext) {
        return new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    @ActivityScope
    @Named("horizontal")
    static LinearLayoutManager getLayoutManagerHorizontal(Context mContext){
        return new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
    }

    @Provides
    @ActivityScope
    static BookmarkAdapter getBookmarkAdapter(Context context) {
        return new BookmarkAdapter(context, new ArrayList<>(), new BookmarkActivity());
    }

    @Provides
    @ActivityScope
    static List<BookmarkValues> list() {
        return new ArrayList<>();
    }

}
