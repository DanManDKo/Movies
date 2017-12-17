package com.example.danman.movies.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.danman.movies.R;
import com.example.danman.movies.manager.ApiManager;
import com.squareup.picasso.Picasso;

/**
 * Created by User on 17.12.2017.
 */

public class BindingUtils {
    @BindingAdapter({"bind:posterUrl"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(ApiManager.IMAGE_SERVER + url)
                .placeholder(R.drawable.movie_img)
                .into(view);
    }
}
