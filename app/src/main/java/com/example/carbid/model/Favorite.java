package com.example.carbid.model;

import java.io.Serializable;

public class Favorite implements Serializable {
    long id_favorite;
    long id_car;

    public long getId_favorite() {
        return id_favorite;
    }

    public void setId_favorite(long id_favorite) {
        this.id_favorite = id_favorite;
    }

    public long getId_car() {
        return id_car;
    }

    public void setId_car(long id_car) {
        this.id_car = id_car;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    long id_user;
}
