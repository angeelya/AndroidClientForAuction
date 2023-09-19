package com.example.carbid.model;

import java.io.Serializable;
import java.util.List;

public class AuctionRequestAdd implements Serializable {
    long id_location;
    String date_auction;
    String name;
    String bid_increase;
    List<CarId> carIdList;

    public List<CarId> getCarIdList() {
        return carIdList;
    }

    public void setCarIdList(List<CarId> carIdList) {
        this.carIdList = carIdList;
    }

    public long getId_location() {
        return id_location;
    }

    public void setId_location(long id_location) {
        this.id_location = id_location;
    }

    public String getDate_auction() {
        return date_auction;
    }

    public void setDate_auction(String date_auction) {
        this.date_auction = date_auction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBid_increase() {
        return bid_increase;
    }

    public void setBid_increase(String bid_increase) {
        this.bid_increase = bid_increase;
    }
}
