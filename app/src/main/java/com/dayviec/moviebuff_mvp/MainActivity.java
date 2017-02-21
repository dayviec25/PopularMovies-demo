package com.dayviec.moviebuff_mvp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dayviec.moviebuff_mvp.databinding.ActivityMainBinding;
import com.dayviec.moviebuff_mvp.di.DaggerNetworkComponent;
import com.dayviec.moviebuff_mvp.di.NetworkComponent;
import com.dayviec.moviebuff_mvp.di.NetworkModule;
import com.dayviec.moviebuff_mvp.model.Movie;
import com.dayviec.moviebuff_mvp.presentation.PopularMediaPresenter;
import com.dayviec.moviebuff_mvp.presentation.PopularMediaPresenterImpl;
import com.dayviec.moviebuff_mvp.view.PopularMediaView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements PopularMediaView{

    ActivityMainBinding binding;
    RecyclerView moviePosterView;
    NetworkComponent networkComponent;
    PopularMediaPresenter presenter;


    @Inject
    MovieService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
        networkComponent.inject(this);
        presenter = new PopularMediaPresenterImpl(service,this);
        binding.movieRecyclerview.setLayoutManager(new GridLayoutManager(this,2));
        //binding.movieRecyclerview.addItemDecoration(new Margin);
        //Call<Movie> service.getPopularMovies(BuildConfig.APIKEY,1).enqueue();

        presenter.getPopularMovies();
        //binding.movieRecyclerview.setAdapter(new);

    }

    public void displayPopularMovies(List<Movie> movies) {
        binding.movieRecyclerview.setAdapter(new MoviePosterAdapter(this, movies, new RecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

            }
        })
        );
    }

    public void loadAdditionalMovies(List<Movie> movies) {

    }

}
