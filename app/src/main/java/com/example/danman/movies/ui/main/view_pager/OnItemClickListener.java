package com.example.danman.movies.ui.main.view_pager;

/**
 * Created by User on 10.12.2017.
 */

public interface OnItemClickListener<T> {
    void onItemClick(T t);

    void onItemStateChanged(T t, boolean state);
}
