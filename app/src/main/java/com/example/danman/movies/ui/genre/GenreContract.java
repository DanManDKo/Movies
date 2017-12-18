package com.example.danman.movies.ui.genre;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.danman.movies.data.Genre;

import java.util.List;

/**
 * Created by User on 18.12.2017.
 */

public interface GenreContract {
    interface View {
        Context getContext();

        void setGenres(List<Genre> genres);

        void startActivity(Intent intent);
    }

    interface Presenter {
        void onDestroy();

        void onNavItemSelected(MenuItem item);

        void onStart();
    }
}
