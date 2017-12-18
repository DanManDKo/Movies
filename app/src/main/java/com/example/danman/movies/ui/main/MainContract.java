package com.example.danman.movies.ui.main;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.danman.movies.data.Movie;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by User on 09.12.2017.
 */

public interface MainContract {
    interface View {
        void startActivity(Intent intent);

        Context getContext();
    }

    interface Presenter {
        void onDestroy();


        void onItemClick(Movie movie);

        boolean onNavItemSelected(MenuItem item);
    }
}
