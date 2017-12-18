package com.example.danman.movies.ui.search;

import android.content.Intent;
import android.view.MenuItem;

import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.example.danman.movies.manager.ApiManager;
import com.example.danman.movies.ui.detail.DetailActivity;
import com.example.danman.movies.ui.genre.GenreActivity;
import com.example.danman.movies.ui.main.MainActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by User on 18.12.2017.
 */

public class SearchPresenter implements SearchContract.Presenter {
    private SearchContract.View mView;
    private ApiManager mApiManager;

    public SearchPresenter(SearchContract.View view, ApiManager apiManager) {
        mView = view;
        mApiManager = apiManager;
    }

    @Override
    public void onQueryTextSubmit(String query) {
        mApiManager.findMovieByName(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(moviesResponse -> moviesResponse.getMovies())
                .subscribe((movies, throwable) -> mView.setMovies(movies));

    }

    @Override
    public void onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_main:
                startActivity(MainActivity.class);
                break;
            case R.id.nav_genre:
                startActivity(GenreActivity.class);
                break;
        }
    }

    @Override
    public void onMovieClick(Movie movie) {
        Intent intent = new Intent(mView.getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        mView.startActivity(intent);
    }

    @Override
    public void onFabClick() {
        String text = mView.getSearchText();
        onQueryTextSubmit(text);
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
