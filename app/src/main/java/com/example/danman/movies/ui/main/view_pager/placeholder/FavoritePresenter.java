package com.example.danman.movies.ui.main.view_pager.placeholder;

import com.example.danman.movies.manager.db.DbManager;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by User on 17.12.2017.
 */

public class FavoritePresenter implements PlaceholderContract.Presenter {
    private PlaceholderContract.View mView;
    private DbManager mDbManager;

    public FavoritePresenter(PlaceholderContract.View view, DbManager dbManager) {
        mView = view;
        mDbManager = dbManager;
    }

    public void onCreate() {
        mDbManager.getFavoriteMovies()
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(movies -> mView.setMovies(movies));
    }

    @Override
    public void onDestroy() {
        mView = null;
        mDbManager = null;
    }

    private void getMovies() {
    }

    @Override
    public void onRefresh() {
        getMovies();
    }
}
