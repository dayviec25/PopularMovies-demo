package com.dayviec.moviebuff_mvp;

import android.animation.Animator;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.dayviec.moviebuff_mvp.databinding.ActivityMediaDetailBinding;
import com.dayviec.moviebuff_mvp.di.DaggerNetworkComponent;
import com.dayviec.moviebuff_mvp.di.NetworkComponent;
import com.dayviec.moviebuff_mvp.di.NetworkModule;
import com.dayviec.moviebuff_mvp.model.Movie;
import com.dayviec.moviebuff_mvp.presentation.MediaDetailPresenter;
import com.dayviec.moviebuff_mvp.presentation.MediaDetailPresenterImpl;
import com.dayviec.moviebuff_mvp.presentation.PopularMediaPresenter;
import com.dayviec.moviebuff_mvp.view.MediaDetailView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by davidchung on 2017-02-24.
 */

public class MediaDetailActivity extends AppCompatActivity implements MediaDetailView{


    ActivityMediaDetailBinding binding;
    NetworkComponent networkComponent;
    MediaDetailPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_media_detail);
        networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
        networkComponent.inject(this);
        presenter = new MediaDetailPresenterImpl(this);
        presenter.getMediaDataFromIntent(getIntent());

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
                final Animator animator = ViewAnimationUtils.createCircularReveal(binding.toolbar, binding.cardInfo.getRight() / 2, binding.cardInfo.getTop() / 2, 0, binding.cardInfo.getWidth() + binding.cardInfo.getHeight());
                Handler handler = new Handler();
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.setDuration(600);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.toolbar.setVisibility(View.VISIBLE);
                        animator.start();
                    }
                }, 300);
            }
        });

    }

    public void displayMovieDetails(Movie movie){
        setToolbarImageBackground(movie);
        binding.cardInfo.setTransitionGroup(false);
        binding.mediaDescription.setText(movie.getOverview());
        binding.mediaSubtitle.setText(movie.getReleaseDate());
        binding.mediaTitle.setText(movie.getTitle());
        Picasso.with(this).load(BuildConfig.IMAGEURL + movie.getPosterPath()).into(binding.movieCover);
    }

    public void setImagePosterTransition(String transitionName){
        binding.movieCover.setTransitionName(transitionName);
    }

    public void setToolbarImageBackground(Movie movie){
        /*
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Drawable drawable = new BitmapDrawable(getResources(),bitmap);
                binding.toolbar.setBackground(drawable);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };*/

        Picasso.with(this).load(BuildConfig.W500IMAGEURL + movie.getBackdropPath()).into(binding.toolbar);
        Log.v("test",BuildConfig.W500IMAGEURL + movie.getBackdropPath());

    }

    public void setMediaEnterTransition() {

        Slide slideFromBottom = new Slide(Gravity.BOTTOM);
        slideFromBottom.setDuration(500);
        slideFromBottom.setInterpolator(new DecelerateInterpolator());
        slideFromBottom.addTarget(R.id.media_description);
        slideFromBottom.addTarget(R.id.media_subtitle);
        slideFromBottom.addTarget(R.id.media_title);

        Fade fade = new Fade();
        fade.setDuration(1000);
        fade.addTarget(R.id.media_description);
        fade.addTarget(R.id.media_subtitle);
        fade.addTarget(R.id.media_title);

        TransitionSet transitionSetInfo = new TransitionSet();
        transitionSetInfo.addTransition(slideFromBottom);
        transitionSetInfo.addTransition(fade);


        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(transitionSetInfo);
        transitionSet.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);

        getWindow().setEnterTransition(transitionSet);
    }

    @Override
    public void onBackPressed() {
        makeExitTransition();
        super.onBackPressed();
    }

    private void makeExitTransition(){
        binding.cardInfo.setTransitionGroup(true);

        Slide slideFromUp = new Slide(Gravity.TOP);
        slideFromUp.setInterpolator(new AccelerateDecelerateInterpolator());
        slideFromUp.addTarget(R.id.toolbar);

        Slide slideFromBottom = new Slide(Gravity.BOTTOM);
        slideFromBottom.setInterpolator(new AccelerateDecelerateInterpolator());
        slideFromBottom.addTarget(R.id.card_info);

        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(slideFromBottom);
        transitionSet.addTransition(slideFromUp);

        getWindow().setExitTransition(transitionSet);
        getWindow().setReturnTransition(transitionSet);
    }

}
