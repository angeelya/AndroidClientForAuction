package com.example.carbid.model;

import java.io.Serializable;

public class AuctionListRequest implements Serializable {

    long id_auction;
    String location;
    String date_auction;
    String name;
    long bid_increase;
    String status;

    public long getId_auction() {
        return id_auction;
    }

    public void setId_auction(long id_auction) {
        this.id_auction = id_auction;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public long getBid_increase() {
        return bid_increase;
    }

    public void setBid_increase(long bid_increase) {
        this.bid_increase = bid_increase;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
