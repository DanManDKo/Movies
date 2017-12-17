package com.example.danman.movies.ui.main;

import android.content.Intent;

import com.example.danman.movies.data.Movie;
import com.example.danman.movies.ui.detail.DetailActivity;


/**
 * Created by User on 09.12.2017.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;


    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(mView.getContext(), DetailActivity.class);
        intent.putExtra(MainActivity.EXTRA_MOVIE, movie);
        mView.startActivity(intent);
    }

    public void onDestroy() {
        mView = null;

    }
}
