package com.dayviec.moviebuff_mvp.presentation;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import com.dayviec.moviebuff_mvp.BuildConfig;
import com.dayviec.moviebuff_mvp.MovieDetailActivity;
import com.dayviec.moviebuff_mvp.MovieService;
import com.dayviec.moviebuff_mvp.model.Movie;
import com.dayviec.moviebuff_mvp.view.PopularMovieView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dayviec on 19/02/17.
 */

public class PopularMoviePresenterImpl implements PopularMoviePresenter {

    private final static int VISIBLE_THRESHOLD = 4;
    private final static int RESPONSE_COUNT = 20;
    private static final String EXTRA_MOVIE = "extra_movie";
    private static final String EXTRA_POSTER_TRANSITION = "extra_transition";

    private String TAG;
    private MovieService service;
    private CompositeSubscription subscriptions;
    private PopularMovieView view;
    private List<Movie> movieList = new ArrayList<Movie>();

    public PopularMoviePresenterImpl(MovieService service, PopularMovieView view) {
        this.TAG = "PopularMoviePresenterImpl";
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getPopularMovies() {
        subscriptions.add(service.getPopularMovies(BuildConfig.APIKEY, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                        Log.v(TAG,"onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG,"onError:" + e.getMessage());
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        view.displayPopularMovies(movies);
                    }
                }));
    }


    public void getAdditionalMoviesByPage(int page) {
        subscriptions.add(service.getPopularMovies(BuildConfig.APIKEY, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                        Log.v(TAG,"onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v(TAG,"onError:" + e.getMessage());
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        view.displayPopularMovies(movies);
                    }
                }));
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop(){
        subscriptions.unsubscribe();
    }

    public void openMovieDetails(Activity activity, Movie movie, ImageView ivImagePoster){
        Gson gson = new Gson();
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE,gson.toJson(movie));
        String transitionName = ivImagePoster.getTransitionName();
        intent.putExtra(EXTRA_POSTER_TRANSITION,transitionName);
        view.displayMovieDetailsView(intent,ivImagePoster,transitionName);
    }

    public void checkToLoadAdditionalMovies(int currentItemCount, int lastVisibleItemPosition ){
        if (lastVisibleItemPosition > currentItemCount - VISIBLE_THRESHOLD){
            //TODO change logic to use page node from response.
            getAdditionalMoviesByPage(Math.round(currentItemCount/RESPONSE_COUNT) + 1);
        }
    }
}
