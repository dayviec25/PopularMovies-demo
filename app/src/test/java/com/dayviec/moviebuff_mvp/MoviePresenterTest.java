package com.dayviec.moviebuff_mvp;

import com.dayviec.moviebuff_mvp.model.Movie;
import com.dayviec.moviebuff_mvp.presentation.PopularMoviePresenter;
import com.dayviec.moviebuff_mvp.presentation.PopularMoviePresenterImpl;
import com.dayviec.moviebuff_mvp.view.PopularMovieView;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by davidchung on 2017-10-14.
 */

@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterTest extends TestCase{

    @Mock
    private MovieService movieService;
    @Mock
    private PopularMovieView popularMovieView;

    private String apiKey = BuildConfig.APIKEY;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchValidDataShouldLoadIntoView() {

        int page = 1;

        List<Movie> popularMovieList = new ArrayList<>();

        Movie movieMock = new Movie();
        movieMock.setId(1);

        popularMovieList.add(movieMock);

        when(movieService.getPopularMovies(apiKey,page)).thenReturn(Observable.just(popularMovieList));

        PopularMoviePresenter popularMoviePresenter = new PopularMoviePresenterImpl(movieService,popularMovieView);

        popularMoviePresenter.getPopularMovies();

        InOrder inOrder = Mockito.inOrder(popularMovieView);

        inOrder.verify(popularMovieView, times(1)).displayPopularMovies(popularMovieList);


    }


}
