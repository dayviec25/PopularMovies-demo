package com.dayviec.moviebuff_mvp;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by davidchung on 2017-10-23.
 */

public class TestSchedulerProvider implements SchedulerProvider{
    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }
}
