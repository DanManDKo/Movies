package com.example.danman.movies.ui.detail;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.example.danman.movies.manager.DbManager;

/**
 * Created by User on 11.12.2017.
 */

public class DetailPresenter implements DetailContract.Presenter {
    private DetailContract.View mView;
    private DbManager mDbManager;

    public DetailPresenter(DetailContract.View view, DbManager dbManager) {
        mView = view;
        mDbManager = dbManager;
    }


    @Override
    public void onFabClick(View view, Movie movie) {
        if (movie.isFavorite() == true) {
            mView.showSnackBar(view, R.string.message_added_to_favorite, Snackbar.LENGTH_LONG);
            mView.changeFabIcon(R.drawable.ic_star_fill);
        } else {
            mView.showSnackBar(view, R.string.message_removed_to_favorite, Snackbar.LENGTH_LONG);
            mView.changeFabIcon(R.drawable.ic_star);
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
        mDbManager = null;
    }
}
