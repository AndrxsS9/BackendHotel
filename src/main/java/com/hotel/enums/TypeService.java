package com.hotel.enums;

public enum TypeService {
    SPA(50.0, false),
    BREAKFAST(15.0, true),
    TRANSFER(30.0, false),
    GYM(15.0, true),
    ROOM_SERVICE(25.0, false);

    private final double basePrice;
    private final boolean perNight;

    TypeService(double basePrice, boolean perNight) {
        this.basePrice = basePrice;
        this.perNight = perNight;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public boolean isPerNight() {
        return perNight;
    }
}
