package com.dayviec.moviebuff_mvp.presentation;

import com.dayviec.moviebuff_mvp.model.Movie;

/**
 * Created by dayviec on 19/02/17.
 */

public interface PopularMediaPresenter extends LifecyclePresenter{
    void getPopularMovies();
    void getAdditionalMoviesByPage(int page);
}
