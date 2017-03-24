package com.dayviec.moviebuff_mvp.view;

import android.graphics.Bitmap;

import com.dayviec.moviebuff_mvp.model.Movie;

/**
 * Created by davidchung on 2017-02-24.
 */

public interface MovieDetailView {
    void displayMovieDetails(Movie movie);
    void setImagePosterTransition(String transitionName);
    void setMediaEnterTransition();
}
