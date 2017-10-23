package com.dayviec.moviebuff_mvp;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by davidchung on 2017-10-23.
 */

final class AppSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }
}