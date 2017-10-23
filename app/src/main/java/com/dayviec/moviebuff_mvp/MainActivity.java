package com.dayviec.moviebuff_mvp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.dayviec.moviebuff_mvp.databinding.ActivityMainBinding;
import com.dayviec.moviebuff_mvp.di.DaggerNetworkComponent;
import com.dayviec.moviebuff_mvp.di.NetworkComponent;
import com.dayviec.moviebuff_mvp.di.NetworkModule;
import com.dayviec.moviebuff_mvp.model.Movie;
import com.dayviec.moviebuff_mvp.presentation.PopularMoviePresenter;
import com.dayviec.moviebuff_mvp.presentation.PopularMoviePresenterImpl;
import com.dayviec.moviebuff_mvp.view.PopularMovieView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements PopularMovieView {

    ActivityMainBinding binding;
    NetworkComponent networkComponent;
    PopularMoviePresenter presenter;
    GridLayoutManager gridLayoutManager;
    MoviePosterAdapter moviePosterAdapter;

    @Inject
    MovieService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
        networkComponent.inject(this);
        presenter = new PopularMoviePresenterImpl(service,this);

        gridLayoutManager = new GridLayoutManager(this,2);
        binding.movieRecyclerview.setLayoutManager(gridLayoutManager);
        moviePosterAdapter = new MoviePosterAdapter(this,new ArrayList<Movie>(), new MoviePosterAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie, ImageView ivMoviePoster) {
                presenter.openMovieDetails(MainActivity.this,movie,ivMoviePoster);
            }
        });

        binding.movieRecyclerview.setAdapter(moviePosterAdapter);
        presenter.getPopularMovies();

        binding.movieRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_SETTLING){
                    presenter.checkToLoadAdditionalMovies(moviePosterAdapter.getItemCount(),gridLayoutManager.findLastVisibleItemPosition());
                }
            }
        });

    }

    @Override
    public void displayPopularMovies(List<Movie> movies) {
        moviePosterAdapter.addMoves(movies);
    }

    @Override
    public void updateSearchView(List<Movie> movies) {
        //TODO: We will implement this in another lesson.
    }

    @Override
    public void displayMovieDetailsView(Intent intent,ImageView ivMoviePoster, String transitionName){
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,ivMoviePoster,transitionName).toBundle());
    }
}
