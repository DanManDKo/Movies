package com.example.danman.movies.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.danman.movies.data.Movie;

import java.util.List;

/**
 * Created by User on 18.12.2017.
 */

public interface SearchContract {
    interface View {
        String getSearchText();
        Context getContext();
        void setMovies(List<Movie> movies);

        void startActivity(Intent intent);
    }

    interface Presenter {
        void onDestroy();

        void onQueryTextSubmit(String query);

        void onMovieClick(Movie movie);

        void onFabClick();


        void onNavigationItemSelected(MenuItem item);
    }
}
