package com.example.danman.movies.data;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by User on 10.12.2017.
 */

public class Genre extends RealmObject {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
