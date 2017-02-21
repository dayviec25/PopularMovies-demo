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
    RecyclerItemClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMoviePoster;
        TextView tvMovieTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ivMoviePoster = (ImageView) itemView.findViewById(R.id.movie_poster);
            this.tvMovieTitle = (TextView) itemView.findViewById(R.id.movie_title);
        }
    }
    public MoviePosterAdapter(Context context, List<Movie> movieList, RecyclerItemClickListener listener) {
        this.TAG = "MovieGridView";
        this.movieList = movieList;
        this.context = context;
        this.listener = listener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, null);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            Movie movie = movieList.get(position);
            Picasso.with(context).load(BuildConfig.IMAGEURL + movie.getPosterPath()).into(holder.ivMoviePoster);
            holder.tvMovieTitle.setText(movie.getTitle());
        }catch (Exception e){

        }
    }

    public int getItemCount() {
        return this.movieList.size();
    }
}
