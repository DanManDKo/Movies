package com.example.danman.movies.api.genre;



import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by User on 18.12.2017.
 */

public interface GenreService {
    String GENRES = "/3/genre/movie/list";
    @GET(GENRES)
    Single<GenreResponse> getGenres();
}
