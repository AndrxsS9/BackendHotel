package com.hotel.enums;

public enum TypeRoom {
    SIMPLE(80.0),
    SHARED(100.0),
    PRIVATE(150.0),
    LUXURY(200.0),
    SUITE(250.0),
    PENTHOUSE(300.0);

    private final double basePrice;

    TypeRoom(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }
}
