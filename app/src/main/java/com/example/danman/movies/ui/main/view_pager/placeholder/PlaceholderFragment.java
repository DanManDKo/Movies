package com.example.danman.movies.ui.main.view_pager.placeholder;

/**
 * Created by User on 09.12.2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danman.movies.App;
import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;
import com.example.danman.movies.ui.main.view_pager.MoviesAdapter;
import com.example.danman.movies.ui.main.view_pager.OnItemClickListener;

import java.util.List;


public class PlaceholderFragment extends Fragment implements OnItemClickListener<Movie>, PlaceholderContract.View {
    public static final int TYPE_POPULAR = 1;
    public static final int TYPE_FAVORITE = 2;
    private static final String KEY_TYPE = "type";
    private MoviesAdapter mAdapter;
    private OnItemClickListener<Movie> mOnItemClickListener;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int mType;
    private PlaceholderContract.Presenter mPresenter;
    public static PlaceholderFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(KEY_TYPE, type);
        PlaceholderFragment fragment = new PlaceholderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnItemClickListener = (OnItemClickListener<Movie>) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException("Must implement om.example.danman.movies.ui.main.view_pager.OnItemClickListener ");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        mType = getArguments().getInt(KEY_TYPE);
        if (mType == TYPE_POPULAR) {
            mPresenter = new PopularPresenter(this, App.getDbManager(), App.getApiManager());
        } else {
            mPresenter = new FavoritePresenter(this, App.getDbManager());
        }
        initViews(view);
        mPresenter.onCreate();
        return view;
    }

    private void initViews(View view) {
        initRecycler(view);
        initSwipe(view);
    }

    private void initRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_fragment_movies);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                mPresenter.onPageChanged(page);
            }
        });
        mAdapter = new MoviesAdapter();
        mAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    private void initSwipe(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipe);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.onRefresh());
    }


    @Override
    public void onItemClick(Movie movie) {
        mOnItemClickListener.onItemClick(movie);
    }

    public void setRefreshState(boolean state) {
        mSwipeRefreshLayout.setRefreshing(state);
    }

    @Override
    public void setMovies(List<Movie> movies) {
        mAdapter.clear();
        mAdapter.addAll(movies);
        mAdapter.notifyDataSetChanged();
        setRefreshState(false);
    }
}
