package com.hotel.model;

import com.hotel.enums.TypeRoom;

public class Room {

    private String id;
    private int number;
    private TypeRoom type;
    private double basePrice;
    private boolean available;

    public Room() {
    }

    public Room(String id, int number, TypeRoom type) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.basePrice = type.getBasePrice();
        this.available = true;
    }

    // --- Getters & Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TypeRoom getType() {
        return type;
    }

    public void setType(TypeRoom type) {
        this.type = type;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
