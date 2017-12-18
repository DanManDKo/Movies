package com.example.danman.movies.ui.main.view_pager.placeholder;

import com.example.danman.movies.manager.ApiManager;
import com.example.danman.movies.manager.db.DbManager;
import com.example.danman.movies.utils.NetworkUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 17.12.2017.
 */

public class PopularPresenter implements PlaceholderContract.Presenter {
    private PlaceholderContract.View mView;
    private DbManager mDbManager;
    private ApiManager mApiManager;
    private int mPage;

    public PopularPresenter(PlaceholderContract.View view, DbManager dbManager, ApiManager apiManager) {
        mView = view;
        mDbManager = dbManager;
        mApiManager = apiManager;
    }

    public void onCreate() {
        mPage = 1;
        getMovies(mPage);
    }

    private void getMovies(int page) {
        if (NetworkUtils.isOnline(mView.getActivity())) {
            getMoviesFullCycle(page);
        } else {
            getCachedMovies();
        }
    }

    private void getCachedMovies() {
        mView.setMovies(mDbManager.getMovies());
    }

    private void getMoviesFullCycle(int page) {
        mApiManager.getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(moviesResponse -> {
                    mDbManager.insertOrUpdateMovies(moviesResponse.getMovies(), true);
                    return moviesResponse.getMovies();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(movies ->
                        mDbManager.getMovies())

                .subscribe(movies ->
                        mView.setMovies(movies), throwable -> throwable.printStackTrace());
    }

    @Override
    public void onPageChanged(int page) {
        mPage = page;
        getMovies(page);
    }

    @Override
    public void onRefresh() {
        getMovies(mPage);
    }

    @Override
    public void onDestroy() {
        mDbManager = null;
        mView = null;
        mApiManager = null;
    }
}
