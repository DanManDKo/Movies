package com.example.danman.movies.ui.main;

import com.example.danman.movies.data.Movie;

import java.util.List;

/**
 * Created by User on 09.12.2017.
 */

public interface MainContract {
    interface View {
        void setPopularMovies(List<Movie> movies);
    }

    interface Presenter {
        void onDestroy();

        void onStart();
    }
}
