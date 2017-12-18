package com.example.danman.movies.api.movies;

import com.example.danman.movies.data.Genre;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by User on 10.12.2017.
 */

public interface MoviesService {
    String POPULAR_MOVIES = "/3/discover/movie?sort_by=popularity.des";
    String GENRES = "/3/genre/movie/list";
    String SEARCH = "/3/search/movie";

    @GET(POPULAR_MOVIES)
    Single<MoviesResponse> getPopularMovies(@QueryMap Map<String, Object> queries);

    @GET(SEARCH)
    Single<MoviesResponse> findMovieByName(@Query("query") String name);

    @GET(GENRES)
    Single<List<Genre>> getGenres();
}
