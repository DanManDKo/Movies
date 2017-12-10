package com.example.danman.movies.ui.main.view_pager;


import java.util.List;

/**
 * Created by User on 09.12.2017.
 */

public interface OnDataReceiveListener<T> {
    void onDataReceive(List<T> data);
}
