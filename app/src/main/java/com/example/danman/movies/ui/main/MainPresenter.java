package com.example.danman.movies.ui.main;

import com.example.danman.movies.manager.ApiManager;
import com.example.danman.movies.manager.DbManager;

/**
 * Created by User on 09.12.2017.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private ApiManager mApiManager;
    private DbManager mDbManager;

    public MainPresenter(MainContract.View view, ApiManager apiManager, DbManager DbManager) {
        mView = view;
        mApiManager = apiManager;
        mDbManager = DbManager;
    }

    @Override
    public void onStart() {
        mApiManager.getPopularMovies().subscribe(
                moviesResponse ->mView.setPopularMovies(moviesResponse.getMovies()) ,
                throwable -> throwable.printStackTrace());
    }

    public void onDestroy() {
        mView = null;
        mApiManager = null;
        mDbManager = null;
    }
}
