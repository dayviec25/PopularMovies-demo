package com.dayviec.moviebuff_mvp.presentation;

import android.util.Log;

import com.dayviec.moviebuff_mvp.BuildConfig;
import com.dayviec.moviebuff_mvp.MovieService;
import com.dayviec.moviebuff_mvp.model.Movie;
import com.dayviec.moviebuff_mvp.view.PopularMediaView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by dayviec on 19/02/17.
 */

public class PopularMediaPresenterImpl implements PopularMediaPresenter {

    private String TAG;
    private MovieService service;
    private CompositeSubscription subscriptions;
    private PopularMediaView view;

    public PopularMediaPresenterImpl(MovieService service, PopularMediaView view) {
        this.TAG = "PopularMediaPresenterImpl";
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


}
