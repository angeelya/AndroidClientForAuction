package com.example.carbid.model;

import java.io.Serializable;

public class Buy implements Serializable {

    long id_buy;
    long id_car;
    long id_user;
    double fullprice;
    double auctionfee;
    double delivery;

    public long getId_buy() {
        return id_buy;
    }

    public void setId_buy(long id_buy) {
        this.id_buy = id_buy;
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

    public double getFullprice() {
        return fullprice;
    }

    public void setFullprice(double fullprice) {
        this.fullprice = fullprice;
    }

    public double getAuctionfee() {
        return auctionfee;
    }

    public void setAuctionfee(double auctionfee) {
        this.auctionfee = auctionfee;
    }

    public double getDelivery() {
        return delivery;
    }

    public void setDelivery(double delivery) {
        this.delivery = delivery;
    }

}
