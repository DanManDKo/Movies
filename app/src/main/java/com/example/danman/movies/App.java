package com.example.danman.movies;

import android.app.Application;

import com.example.danman.movies.manager.ApiManager;
import com.example.danman.movies.manager.DbManager;

/**
 * Created by User on 09.12.2017.
 */

public class App extends Application {
    private static ApiManager sApiManager;
    private static DbManager sDbManager;

    public static ApiManager getApiManager() {
        if (sApiManager == null) {
            sApiManager = new ApiManager();
        }
        return sApiManager;
    }

    public static DbManager getDbManager() {
        if (sDbManager == null) {
            sDbManager = new DbManager();
        }
        return sDbManager;
    }
}
