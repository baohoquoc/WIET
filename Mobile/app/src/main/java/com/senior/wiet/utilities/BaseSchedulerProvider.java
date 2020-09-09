package com.senior.wiet.utilities;

import androidx.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by lance on 18/February/2020.
 */
public interface BaseSchedulerProvider {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
