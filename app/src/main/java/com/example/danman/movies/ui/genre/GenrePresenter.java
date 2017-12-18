package com.example.danman.movies.ui.genre;

import android.content.Intent;
import android.view.MenuItem;

import com.example.danman.movies.R;
import com.example.danman.movies.manager.ApiManager;
import com.example.danman.movies.ui.main.MainActivity;
import com.example.danman.movies.ui.search.SearchActivity;
import com.example.danman.movies.utils.NetworkUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 18.12.2017.
 */

public class GenrePresenter implements GenreContract.Presenter {
    private GenreContract.View mView;
    private ApiManager mApiManager;

    public GenrePresenter(GenreContract.View view, ApiManager apiManager) {
        mView = view;
        mApiManager = apiManager;
    }


    @Override
    public void onNavItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_main:
                startActivity(MainActivity.class);
                break;
            case R.id.nav_search:
                startActivity(SearchActivity.class);
                break;
        }
    }

    @Override
    public void onStart() {
        if (NetworkUtils.isOnline(mView.getContext())==false){
            return;
        }
        mApiManager.getGenres()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genre -> mView.setGenres(genre.getGenres()), throwable -> throwable.printStackTrace());
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(mView.getContext(), clazz);
        mView.startActivity(intent);
    }

    public void onDestroy() {
        mView = null;
        mApiManager = null;
    }
}
