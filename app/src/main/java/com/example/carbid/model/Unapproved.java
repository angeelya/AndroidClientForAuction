package com.example.carbid.model;

import java.io.Serializable;

public class Unapproved implements Serializable {
    long id_unapproved;

    public long getId_unapproved() {
        return id_unapproved;
    }

    public void setId_unapproved(long id_unapproved) {
        this.id_unapproved = id_unapproved;
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

    long id_car;
    long id_user;
}
