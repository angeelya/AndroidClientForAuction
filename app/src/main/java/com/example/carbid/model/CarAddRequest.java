package com.example.carbid.model;

import java.io.Serializable;
import java.util.List;

public class CarAddRequest implements Serializable {
    long id_car;
    long id_typecar;
    long id_model;
    String reserve_price;
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
    String min_bid;
    List<ImageCar> images;

    public long getId_car() {
        return id_car;
    }

    public void setId_car(long id_car) {
        this.id_car = id_car;
    }

    public long getId_typecar() {
        return id_typecar;
    }

    public void setId_typecar(long id_typecar) {
        this.id_typecar = id_typecar;
    }

    public long getId_model() {
        return id_model;
    }

    public void setId_model(long id_model) {
        this.id_model = id_model;
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

    public List<ImageCar> getImages() {
        return images;
    }

    public void setImages(List<ImageCar> images) {
        this.images = images;
    }

    public String getReserve_price() {
        return reserve_price;
    }

    public void setReserve_price(String reserve_price) {
        this.reserve_price = reserve_price;
    }

    public String getMin_bid() {
        return min_bid;
    }

    public void setMin_bid(String min_bid) {
        this.min_bid = min_bid;
    }



}
