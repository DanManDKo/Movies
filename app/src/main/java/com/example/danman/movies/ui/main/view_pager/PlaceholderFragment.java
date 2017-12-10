package com.example.danman.movies.ui.main.view_pager;

/**
 * Created by User on 09.12.2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danman.movies.R;
import com.example.danman.movies.data.Movie;

import java.util.List;


public class PlaceholderFragment extends Fragment implements OnDataReceiveListener<Movie>, OnItemClickListener<Movie> {

    private static final String KEY_IMAGE_SERVER = "image_server";
    private MoviesAdapter mAdapter;
    private OnItemClickListener<Movie> mListener;

    public static PlaceholderFragment newInstance(String imageServer) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(KEY_IMAGE_SERVER, imageServer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnItemClickListener<Movie>) context;
        } catch (ClassCastException ex) {
            throw new ClassCastException("Must implement om.example.danman.movies.ui.main.view_pager.OnItemClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        Bundle args = getArguments();
        String server = "";
        if (args != null) {
            server = args.getString(KEY_IMAGE_SERVER);
        }

        initViews(view, server);
        return view;
    }

    private void initViews(View view, String imageServer) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_fragment_movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MoviesAdapter(this);
        mAdapter.setImageServer(imageServer);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDataReceive(List<Movie> data) {
        mAdapter.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Movie movie) {
        mListener.onItemClick(movie);
    }

    @Override
    public void onItemStateChanged(Movie movie, boolean state) {

    }
}
