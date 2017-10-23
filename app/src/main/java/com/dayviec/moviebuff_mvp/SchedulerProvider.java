package com.dayviec.moviebuff_mvp;

import io.reactivex.Scheduler;

/**
 * Created by davidchung on 2017-10-23.
 */

public interface SchedulerProvider {
    Scheduler ui();
    Scheduler computation();
    Scheduler io();
}
