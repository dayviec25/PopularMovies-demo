package com.dayviec.moviebuff_mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dayviec.moviebuff_mvp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dayviec on 18/02/17.
 */

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.ViewHolder> {

    List<Movie> movieList;
    String TAG;
    Context context;
    private OnMovieClickListener onMovieClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMoviePoster;
        TextView tvMovieTitle;
        TextView tvMovieSubtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivMoviePoster = (ImageView) itemView.findViewById(R.id.movie_cover);
            this.tvMovieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            this.tvMovieSubtitle = (TextView) itemView.findViewById(R.id.movie_subtitle);
        }
    }
    public MoviePosterAdapter(Context context, List<Movie> movieList, OnMovieClickListener onMovieClickListener) {
        this.TAG = "MovieGridView";
        this.movieList = movieList;
        this.context = context;
        this.onMovieClickListener = onMovieClickListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMovieClickListener.onMovieClick(movieList.get(viewHolder.getAdapterPosition()), viewHolder.ivMoviePoster);
            }
        });
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        Picasso.with(context).load(BuildConfig.IMAGEURL + movie.getPosterPath()).into(holder.ivMoviePoster);
        holder.ivMoviePoster.setTransitionName("posterTransition" + position);
        holder.tvMovieTitle.setText(movie.getTitle());
        holder.tvMovieSubtitle.setText(movie.getReleaseDate());
    }

    public int getItemCount() {
        return this.movieList.size();
    }

   public interface OnMovieClickListener {
        void onMovieClick(Movie movie, ImageView ivMoviePoster);
    }
}
