package com.example.danman.movies.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.danman.movies.App;
import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.example.danman.movies.ui.main.view_pager.MoviesAdapter;
import com.example.danman.movies.ui.main.view_pager.OnItemClickListener;

import java.util.List;

public class SearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchContract.View, OnItemClickListener<Movie> {
    private SearchView mSearchView;
    private SearchContract.Presenter mPresenter;
    private MoviesAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        mPresenter = new SearchPresenter(this, App.getApiManager());
    }

    private void initViews() {
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        initRecycler();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> mPresenter.onFabClick());
    }

    @Override
    public String getSearchText() {
        return mSearchView.getQuery().toString();
    }

    @Override
    public void setMovies(List<Movie> movies) {
        mAdapter.clear();
        mAdapter.addAll(movies);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void initRecycler() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MoviesAdapter();
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            mSearchView = (SearchView) searchItem.getActionView();
        }
        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mPresenter.onQueryTextSubmit(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        mPresenter.onNavigationItemSelected(item);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override
    public void onItemClick(Movie movie) {
        mPresenter.onMovieClick(movie);
    }
}
