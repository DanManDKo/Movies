package com.example.danman.movies.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.danman.movies.App;
import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.example.danman.movies.ui.detail.DetailActivity;
import com.example.danman.movies.ui.main.view_pager.OnDataReceiveListener;
import com.example.danman.movies.ui.main.view_pager.OnItemClickListener;
import com.example.danman.movies.ui.main.view_pager.OnRefreshListener;
import com.example.danman.movies.ui.main.view_pager.placeholder.PlaceholderFragment;
import com.example.danman.movies.ui.main.view_pager.SectionsPagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View, OnItemClickListener<Movie> {
    public final static String EXTRA_MOVIE = "extra_movie";
    private MainContract.Presenter mPresenter;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private PlaceholderFragment mPopularMovies;
    private PlaceholderFragment mFavoriteMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mPresenter = new MainPresenter(this);


    }



    private void initViews() {
        initToolbar();
        initViewPager();
        initTabLayout();
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initViewPager() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
        mPopularMovies = PlaceholderFragment.newInstance(PlaceholderFragment.TYPE_POPULAR);
        mFavoriteMovies = PlaceholderFragment.newInstance(PlaceholderFragment.TYPE_FAVORITE);
        mSectionsPagerAdapter.addFragment(mPopularMovies);
        mSectionsPagerAdapter.addFragment(mFavoriteMovies);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    private void initTabLayout() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override
    public void onItemClick(Movie movie) {
        mPresenter.onItemClick(movie);

    }


    @Override
    public Context getContext() {
        return this;
    }
}
