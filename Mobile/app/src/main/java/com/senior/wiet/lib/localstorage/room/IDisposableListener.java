package com.senior.wiet.lib.localstorage.room;

/**
 * Created by lance on 18/February/2020.
 */
public interface IDisposableListener<T> {
    void onComplete();

    void onHandleData(T t);

    void onRequestWrongData(int t, String message);

    void onApiFailure(Throwable error);
}