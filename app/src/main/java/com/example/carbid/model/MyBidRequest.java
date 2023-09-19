package com.example.carbid.model;

import java.io.Serializable;

public class MyBidRequest implements Serializable {

    long id_bid;
    String name;
    long id_user;
    long price;
    String image;
   long id_car;

    public long getId_car() {
        return id_car;
    }

    public void setId_car(long id_car) {
        this.id_car = id_car;
    }

    public long getId_bid() {
        return id_bid;
    }

    public void setId_bid(long id_bid) {
        this.id_bid = id_bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
