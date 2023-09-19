package com.example.carbid.model;

import java.io.Serializable;

public class LocationAuction implements Serializable {
    long id_location;
    String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getId_location() {
        return id_location;
    }

    public void setId_location(long id_location) {
        this.id_location = id_location;
    }

}
