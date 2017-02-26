package com.dayviec.moviebuff_mvp.di;

import com.dayviec.moviebuff_mvp.MainActivity;
import com.dayviec.moviebuff_mvp.MediaDetailActivity;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(MainActivity mainActivity);
    void inject(MediaDetailActivity mediaDetailActivity);
}
