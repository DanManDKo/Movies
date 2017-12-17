package com.example.danman.movies.ui.main;

import com.example.danman.movies.manager.ApiManager;
import com.example.danman.movies.manager.db.DbManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        getMovies();
    }

    private void getMovies() {
        mApiManager.getPopularMovies()
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
                        mView.setPopularMovies(movies), new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void onStart() {


    }

    public void onDestroy() {
        mView = null;
        mApiManager = null;
        mDbManager = null;
    }
}
