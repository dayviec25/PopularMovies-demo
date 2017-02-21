package com.dayviec.moviebuff_mvp.view;


import com.dayviec.moviebuff_mvp.model.Movie;

import java.util.List;

public interface PopularMediaView {

    void displayPopularMovies(List<Movie> movies);
    void loadAdditionalMovies(List<Movie> movies);

}