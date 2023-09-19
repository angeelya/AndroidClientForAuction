package com.example.carbid.model;

import java.io.Serializable;
import java.util.List;

public class CarForCar implements Serializable {
    long id_car;
    String typecar;
    String date_auction;
    String name;
    String year;
    String vin;
    String color;
    String fuel;
    long mileage;
    String document;
    String damage;
    String engine_type;
    long cylinders;
    String drive;
    String type_body;
    String transmission;
    String keys_car;
    List<String> imageList;
    String bid;

    public String getDate_auction() {
        return date_auction;
    }

    public void setDate_auction(String date_auction) {
        this.date_auction = date_auction;
    }
    public String getTypecar() {
        return typecar;
    }

    public void setTypecar(String typecar) {
        this.typecar = typecar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public long getCylinders() {
        return cylinders;
    }

    public void setCylinders(long cylinders) {
        this.cylinders = cylinders;
    }

    public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public String getType_body() {
        return type_body;
    }

    public void setType_body(String type_body) {
        this.type_body = type_body;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getKeys_car() {
        return keys_car;
    }

    public void setKeys_car(String keys_car) {
        this.keys_car = keys_car;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public long getId_car() {
        return id_car;
    }

    public void setId_car(long id_car) {
        this.id_car = id_car;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
