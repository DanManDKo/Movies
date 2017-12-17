package com.example.danman.movies.ui.main.view_pager;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.example.danman.movies.databinding.RvItemMoviesBinding;
import com.example.danman.movies.manager.ApiManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.12.2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private OnItemClickListener mListener;

    public MoviesAdapter(OnItemClickListener listener) {
        mMovies = new ArrayList<>();
        mListener = listener;
    }

    @BindingAdapter({"bind:posterUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(ApiManager.IMAGE_SERVER + url)
                .placeholder(R.drawable.movie_img)
                .into(view);
    }


    public void addAll(List<Movie> movies) {
        mMovies.addAll(movies);
    }

    public void add(Movie movie) {
        mMovies.add(movie);
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RvItemMoviesBinding binding = RvItemMoviesBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = mMovies.get(position);
        holder.mBinding.setMovie(movie);
        holder.itemView.setOnClickListener(view ->mListener.onItemClick(movie));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        RvItemMoviesBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);

        }
    }
}
