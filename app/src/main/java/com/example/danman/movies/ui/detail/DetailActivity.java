package com.example.danman.movies.ui.detail;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.danman.movies.App;
import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.example.danman.movies.databinding.ActivityDetailBinding;
import com.example.danman.movies.manager.ApiManager;
import com.example.danman.movies.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {
    private DetailContract.Presenter mPresenter;
    private Movie mMovie;
    private FloatingActionButton mFabFavorite;

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(ApiManager.IMAGE_SERVER + url)
                .placeholder(R.drawable.movie_img)
                .into(view);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        initViews();
        mMovie = getIntent().getParcelableExtra(MainActivity.EXTRA_MOVIE);
        binding.setMovie(mMovie);

        mPresenter = new DetailPresenter(this, App.getDbManager());

    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFabFavorite = (FloatingActionButton) findViewById(R.id.fab);
        mFabFavorite.setOnClickListener(view -> {
            mMovie.setFavorite(!mMovie.isFavorite());
            mPresenter.onFabClick(view, mMovie);

        });
    }

    @Override
    public void changeFabIcon(int imageId) {
        mFabFavorite.setImageResource(imageId);
    }

    @Override
    public void showSnackBar(View view, int textId, int duration) {
        Snackbar.make(view, textId, duration).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }
}
