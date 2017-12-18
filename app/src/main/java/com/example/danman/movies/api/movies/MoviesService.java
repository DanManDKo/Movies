package com.example.danman.movies.api.movies;

import com.example.danman.movies.data.Genre;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by User on 10.12.2017.
 */

public interface MoviesService {
    String POPULAR_MOVIES = "/3/discover/movie?sort_by=popularity.des";
    String GENRES = "/genre/movie/list";

    @GET(POPULAR_MOVIES)
    Single<MoviesResponse> getPopularMovies(@QueryMap Map<String, Object> queries);


    @GET(GENRES)
    Single<List<Genre>> getGenres();
}
