package com.example.carbid.model;

import java.io.Serializable;

public class Model implements Serializable {
    long id_model;
    long id_brand;
    String name;
    public long getId_model() {
        return id_model;
    }

    public void setId_model(long id_model) {
        this.id_model = id_model;
    }

    public long getId_brand() {
        return id_brand;
    }

    public void setId_brand(long id_brand) {
        this.id_brand = id_brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
