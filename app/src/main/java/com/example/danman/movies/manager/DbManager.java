package com.example.danman.movies.manager;

import android.content.Context;

import com.example.danman.movies.data.Movie;

import java.util.List;

import io.reactivex.Single;
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

    }



    public void remove(Movie movie) {
    }

}
