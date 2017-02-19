package com.dayviec.moviebuff_mvp.di;

import com.dayviec.moviebuff_mvp.MainActivity;

import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(MainActivity mainActivity);
}
