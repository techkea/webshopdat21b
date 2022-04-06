package com.example.webshopdat21b.model;

public class Vendor {

    private String name;

    public Vendor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "name='" + name + '\'' +
                '}';
    }
}
