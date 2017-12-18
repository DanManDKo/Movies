package com.example.danman.movies.ui.genre;

import android.content.Intent;
import android.view.MenuItem;

import com.example.danman.movies.R;
import com.example.danman.movies.manager.ApiManager;
import com.example.danman.movies.ui.main.MainActivity;
import com.example.danman.movies.ui.search.SearchActivity;

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

    private void startActivity(Class clazz) {
        Intent intent = new Intent(mView.getContext(), clazz);
        mView.startActivity(intent);
    }
    public void onDestroy() {
        mView = null;
        mApiManager = null;
    }
}
