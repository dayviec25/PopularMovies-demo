package com.dayviec.moviebuff_mvp.presentation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.dayviec.moviebuff_mvp.MovieService;
import com.dayviec.moviebuff_mvp.model.Movie;
import com.dayviec.moviebuff_mvp.view.MediaDetailView;
import com.dayviec.moviebuff_mvp.view.PopularMediaView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by davidchung on 2017-02-24.
 */

public class MediaDetailPresenterImpl implements MediaDetailPresenter{

    private String TAG;
    private static final String EXTRA_MOVIE = "extra_movie";
    private static final String EXTRA_POSTER_TRANSITION = "extra_transition";

    private CompositeSubscription subscriptions;
    private MediaDetailView view;

    public MediaDetailPresenterImpl(MediaDetailView view) {
        this.TAG = "PopularMediaPresenterImpl";
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void onResume() {

    }

    public void onStop() {

    }

    public void onPause() {

    }


    public void getMediaDataFromIntent(Intent intent) {
        Gson gson = new Gson();
        String strMovie = intent.getStringExtra(EXTRA_MOVIE);
        Movie movie = gson.fromJson(strMovie,Movie.class);
        String posterTransitionName = intent.getStringExtra(EXTRA_POSTER_TRANSITION);
        view.displayMovieDetails(movie);
        view.setImagePosterTransition(posterTransitionName);
        view.setMediaEnterTransition();
    }


}

