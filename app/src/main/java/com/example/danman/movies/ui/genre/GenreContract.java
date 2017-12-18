package com.example.danman.movies.ui.genre;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

/**
 * Created by User on 18.12.2017.
 */

public interface GenreContract {
    interface View {
        Context getContext();
        void setG
        void startActivity(Intent intent);
    }

    interface Presenter {
        void onDestroy();

        void onNavItemSelected(MenuItem item);
    }
}
