package com.example.danman.movies.ui.main.view_pager.placeholder;

import android.content.Context;

import com.example.danman.movies.data.Movie;

import java.util.List;

/**
 * Created by User on 17.12.2017.
 */

public interface PlaceholderContract {
    interface View {
        void setMovies(List<Movie>movies);

        Context getActivity();

    }

    interface Presenter {
        void onCreate();

        void onDestroy();

        void onRefresh();

        void onPageChanged(int page);
    }
}
