package com.dayviec.moviebuff_mvp;

import com.dayviec.moviebuff_mvp.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by dayviec on 17/02/17.
 */

public interface MovieService {
    @GET("/discover/movie?&sort_by=popularity.desc&include_adult=false&include_video=false")
    Call<List<Movie>> getPopularMovies(@Query("api_key") String apiKey,@Query("page")int page);
}
