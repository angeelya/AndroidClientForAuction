package com.example.carbid.model;



public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Long id_destination;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId_destination() {
        return id_destination;
    }

    public void setId_destination(Long id_destination) {
        this.id_destination = id_destination;
    }
}
