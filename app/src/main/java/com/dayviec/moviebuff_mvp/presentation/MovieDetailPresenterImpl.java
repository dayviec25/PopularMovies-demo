package com.dayviec.moviebuff_mvp.presentation;

import android.content.Intent;

import com.dayviec.moviebuff_mvp.model.Movie;
import com.dayviec.moviebuff_mvp.view.MovieDetailView;
import com.google.gson.Gson;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by davidchung on 2017-02-24.
 */

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    private String TAG;
    private static final String EXTRA_MOVIE = "extra_movie";
    private static final String EXTRA_POSTER_TRANSITION = "extra_transition";

    private CompositeDisposable subscriptions;
    private MovieDetailView view;

    public MovieDetailPresenterImpl(MovieDetailView view) {
        this.TAG = "PopularMoviePresenterImpl";
        this.view = view;
        this.subscriptions = new CompositeDisposable();
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

