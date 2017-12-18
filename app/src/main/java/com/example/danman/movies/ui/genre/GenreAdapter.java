package com.example.danman.movies.ui.genre;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danman.movies.data.Genre;
import com.example.danman.movies.databinding.RvItemGenreBinding;
import com.example.danman.movies.ui.main.view_pager.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 18.12.2017.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private OnItemClickListener<Genre> mListener;
    private List<Genre> mItems;

    public GenreAdapter() {
        mItems = new ArrayList<>();
    }

    public void addAll(List<Genre> genres) {
        mItems.addAll(genres);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RvItemGenreBinding binding = RvItemGenreBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Genre genre = mItems.get(position);
        holder.mBinding.setGenre(genre);
        holder.itemView.setOnClickListener(view -> mListener.onItemClick(genre));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setOnItemClickListener(OnItemClickListener<Genre> listener) {
        mListener = listener;
    }

    public void clear() {
        mItems.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RvItemGenreBinding mBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

    }

}
