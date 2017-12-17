package com.example.danman.movies.binding_data;

import com.example.danman.movies.data.Movie;

import java.util.List;

/**
 * Created by User on 11.12.2017.
 */

public class BindingMovie {
    private long voteCount;
    private long id;
    private boolean video;
    private float voteAverage;
    private String title;
    private float popularity;
    private String poster;
    private String originalLanguage;
    private String originalTitle;
    private List<Long> genrIds;
    private String backdropPath;
    private boolean adult;
    private String overview;
    private String releaseDate;
    private boolean favorite;

    public BindingMovie(Movie movie) {
        this.voteCount = movie.getVoteCount();
        this.id = movie.getId();
        this.video = movie.isVideo();
        this.voteAverage = movie.getVoteAverage();
        this.title = movie.getTitle();
        this.popularity = movie.getPopularity();
        this.poster = movie.getPoster();
        this.originalLanguage = movie.getOriginalLanguage();
        this.originalTitle = movie.getOriginalTitle();
        this.genrIds = movie.getGenrIds();
        this.backdropPath = movie.getBackdropPath();
        this.adult = movie.isAdult();
        this.overview = movie.getOverview();
        this.releaseDate = movie.getReleaseDate();
        this.favorite = movie.isFavorite();
    }

    public long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Long> getGenrIds() {
        return genrIds;
    }

    public void setGenrIds(List<Long> genrIds) {
        this.genrIds = genrIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
