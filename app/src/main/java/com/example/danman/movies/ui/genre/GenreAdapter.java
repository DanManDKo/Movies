package com.example.danman.movies.ui.genre;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.danman.movies.data.Genre;
import com.example.danman.movies.ui.main.view_pager.OnItemClickListener;

/**
 * Created by User on 18.12.2017.
 */

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private OnItemClickListener<Genre> mListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setOnItemClickListener(OnItemClickListener<Genre> listener) {
        mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
