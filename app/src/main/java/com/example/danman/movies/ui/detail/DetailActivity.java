package com.example.danman.movies.ui.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.example.danman.movies.manager.ApiManager;
import com.example.danman.movies.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView imageView = (ImageView) findViewById(R.id.iv_detail);
        Movie movie = getIntent().getParcelableExtra(MainActivity.EXTRA_MOVIE);
        if (movie != null) {
            Picasso.with(this).load(ApiManager.IMAGE_SERVER + movie.getPoster()).placeholder(R.drawable.movie_img).into(imageView);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }
}
