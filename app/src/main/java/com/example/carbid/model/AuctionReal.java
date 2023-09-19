package com.example.carbid.model;

import java.io.Serializable;
import java.util.List;

public class AuctionReal implements Serializable {
    long id_car;
    String name_user;
    String name;
    String ourBid;
    String MaxBid;
    String image;

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public long getId_car() {
        return id_car;
    }

    public void setId_car(long id_car) {
        this.id_car = id_car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOurBid() {
        return ourBid;
    }

    public void setOurBid(String ourBid) {
        this.ourBid = ourBid;
    }

    public String getMaxBid() {
        return MaxBid;
    }

    public void setMaxBid(String maxBid) {
        MaxBid = maxBid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
