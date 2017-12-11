package com.example.danman.movies.ui.detail;

import com.example.danman.movies.data.Movie;
import android.view.View;
/**
 * Created by User on 11.12.2017.
 */

public class DetailContract {
    interface View {
        void showSnackBar(android.view.View view, int textId, int duration);

        void changeFabIcon(int imageId);
    }

    interface Presenter {
        void onFabClick(android.view.View view, Movie movie);

        void onDestroy();
    }
}
