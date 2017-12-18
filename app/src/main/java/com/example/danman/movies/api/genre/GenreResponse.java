package com.example.danman.movies.api.genre;

import com.example.danman.movies.data.Genre;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 18.12.2017.
 */

public class GenreResponse {
    @SerializedName("genres")
    private List<Genre>genres;

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
