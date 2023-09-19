package com.example.carbid.model;

import java.io.Serializable;

public class QueryModel implements Serializable {
    long id_query;
    long id_car;
    long id_user;

    public long getId_query() {
        return id_query;
    }

    public void setId_query(long id_query) {
        this.id_query = id_query;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    double price;
}
