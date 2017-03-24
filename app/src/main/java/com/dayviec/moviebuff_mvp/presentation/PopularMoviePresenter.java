package com.dayviec.moviebuff_mvp.presentation;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.dayviec.moviebuff_mvp.model.Movie;

/**
 * Created by dayviec on 19/02/17.
 */

public interface PopularMoviePresenter extends LifecyclePresenter{
    void getPopularMovies();
    void getAdditionalMoviesByPage(int page);
    void openMovieDetails(Activity activity,Movie movie, ImageView ivMoviePoster);
    void checkToLoadAdditionalMovies(int currentItemCount, int lastVisibleItemPosition);
}
