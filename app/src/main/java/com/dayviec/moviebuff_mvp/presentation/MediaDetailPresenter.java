package com.dayviec.moviebuff_mvp.presentation;

import android.content.Intent;

/**
 * Created by davidchung on 2017-02-24.
 */

public interface MediaDetailPresenter extends LifecyclePresenter{
    void getMediaDataFromIntent(Intent intent);
}
