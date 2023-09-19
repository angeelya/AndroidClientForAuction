package com.example.carbid.model;

import java.io.Serializable;

public class Destination implements Serializable {
    long id_destination;
    long id_user;
    String location;
    double coefficient;

    public long getId_destination() {
        return id_destination;
    }

    public void setId_destination(long id_destination) {
        this.id_destination = id_destination;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }
}
