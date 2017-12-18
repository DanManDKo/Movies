package com.example.danman.movies.ui.main;

import android.content.Intent;
import android.view.MenuItem;

import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.example.danman.movies.ui.detail.DetailActivity;
import com.example.danman.movies.ui.genre.GenreActivity;
import com.example.danman.movies.ui.search.SearchActivity;


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
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        mView.startActivity(intent);
    }

    @Override
    public boolean onNavItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_search:
                startActivity(SearchActivity.class);
                break;
            case R.id.nav_genre:
                startActivity(GenreActivity.class);
                break;
        }
        return false;
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(mView.getContext(), clazz);
        mView.startActivity(intent);
    }

    public void onDestroy() {
        mView = null;

    }
}
