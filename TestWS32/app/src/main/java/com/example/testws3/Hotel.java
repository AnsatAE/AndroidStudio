package com.example.testws3;

public class Hotel {
    private int id;
    private String name;
    private String description;
    private double price;
    private String address;

    public Hotel(int id, String name, String description, double price, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }
}

