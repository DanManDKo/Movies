package com.example.danman.movies.ui.main.view_pager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.12.2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<Movie> mMovies;
    private OnItemClickListener mListener;
    private String mImageServer;
    public MoviesAdapter(OnItemClickListener listener) {
        mMovies = new ArrayList<>();
        mListener = listener;
    }

    public void setImageServer(String imageServer) {
        mImageServer = imageServer;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = mMovies.get(position);
        holder.itemView.setOnClickListener(view -> mListener.onItemClick(movie));
        holder.mTvTitle.setText(movie.getTitle());
        holder.mTvOverview.setText(movie.getOverview());
        holder.mChbStar.setChecked(movie.isFavorite());
        holder.mChbStar.setOnCheckedChangeListener((compoundButton, state) -> {
            mListener.onItemStateChanged(movie, state);
        });
        Context context = holder.mIvPoster.getContext();
        Picasso.with(context).load(mImageServer + movie.getPoster()).placeholder(R.drawable.movie_img).into(holder.mIvPoster);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvPoster;
        private TextView mTvTitle;
        private TextView mTvOverview;
        private CheckBox mChbStar;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvPoster = itemView.findViewById(R.id.iv_rv_item_poster);
            mTvTitle = itemView.findViewById(R.id.tv_rv_item_title);
            mTvOverview = itemView.findViewById(R.id.tv_rv_item_overview);
            mChbStar = itemView.findViewById(R.id.iv_rv_item_star);
        }
    }
}
