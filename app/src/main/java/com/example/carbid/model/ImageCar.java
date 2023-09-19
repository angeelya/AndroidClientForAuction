package com.example.carbid.model;

import java.io.Serializable;

public class ImageCar implements Serializable {
    String image64base;
    String name_img;

    public String getImage64base() {
        return image64base;
    }

    public void setImage64base(String image64base) {
        this.image64base = image64base;
    }

    public String getName_img() {
        return name_img;
    }

    public void setName_img(String name_img) {
        this.name_img = name_img;
    }
}
