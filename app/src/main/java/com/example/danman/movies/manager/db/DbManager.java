package com.example.danman.movies.manager.db;

import android.content.Context;

import com.example.danman.movies.data.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by User on 09.12.2017.
 */

public class DbManager {
    private static final String DB_NAME = "movies.realm";
    private Realm mRealmUi;

    public DbManager(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name(DB_NAME).build();
        Realm.setDefaultConfiguration(config);
        mRealmUi = Realm.getDefaultInstance();
    }

    public void saveMovie(Movie movie) {

        executeTransaction(realm -> realm.insertOrUpdate(movie));
    }

    public void insertOrUpdateMovies(List<Movie> movies) {
        executeTransaction(realm -> realm.insertOrUpdate(movies));
    }

    public void insertOrUpdateMovies(List<Movie> movies, boolean notOverrideFavorites) {
        if (notOverrideFavorites == false) {
            insertOrUpdateMovies(movies);
        } else {
            Realm realm = Realm.getDefaultInstance();
            try {
                RealmResults<Movie> favorites = realm.where(Movie.class).equalTo(DbFields.MovieFields.FAVORITE, true).findAll();
                insertOrUpdateMovies(movies);
                insertOrUpdateMovies(favorites);
            } finally {
                if (realm != null) {
                    realm.close();
                }
            }
        }
    }

    public void insertOrUpdateMovie(Movie movie) {
        executeTransaction(realm -> realm.insertOrUpdate(movie));
    }

    public List<Movie> getMovies() {
        Realm realm = Realm.getDefaultInstance();
        List<Movie> movies = new ArrayList<>();
        try {
            movies = realm.where(Movie.class).findAll();
        } finally {
            if (realm != null) {
                realm.close();
            }
            return movies;
        }
    }


    public void remove(Movie movie) {
        executeTransaction(realm -> realm.where(Movie.class).equalTo(DbFields.MovieFields.ID, movie.getId()).findAll().deleteAllFromRealm());
    }

    private void executeTransaction(Realm.Transaction transaction) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(transaction);
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

}
