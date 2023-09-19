package com.example.carbid.model;

import java.io.Serializable;

public class TypeCar implements Serializable {
    long id_typecar;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public long getId_typecar() {
        return id_typecar;
    }

    public void setId_typecar(long id_typecar) {
        this.id_typecar = id_typecar;
    }

    double coefficient;
}
