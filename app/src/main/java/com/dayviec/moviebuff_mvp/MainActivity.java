package com.dayviec.moviebuff_mvp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.dayviec.moviebuff_mvp.databinding.ActivityMainBinding;
import com.dayviec.moviebuff_mvp.di.DaggerNetworkComponent;
import com.dayviec.moviebuff_mvp.di.NetworkComponent;
import com.dayviec.moviebuff_mvp.di.NetworkModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RecyclerView moviePosterView;
    NetworkComponent networkComponent;

    @Inject
    MovieService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();



        binding.movieRecyclerview.setAdapter();

    }
}
