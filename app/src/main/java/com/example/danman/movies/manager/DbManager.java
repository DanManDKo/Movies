package com.example.danman.movies.manager;

import android.content.Context;

import com.example.danman.movies.data.Movie;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by User on 09.12.2017.
 */

public class DbManager {
    private static final String DB_NAME = "movies.realm";

    public DbManager(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name(DB_NAME).build();
        Realm.setDefaultConfiguration(config);
    }

    public void saveMovie(Movie movie) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(realm1 -> realm1.insertOrUpdate(movie));
        realm.close();
    }

    public List<Movie> getAllMovies() {

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Movie> results = realm.where(Movie.class).findAll();
        realm.close();
        return results;
    }

    public void remove(Movie movie) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm.where(Movie.class).equalTo("id", movie.getId()).findAll().deleteAllFromRealm();
        });
    }

}
