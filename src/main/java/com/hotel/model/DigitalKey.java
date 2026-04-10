package com.hotel.model;

import java.time.LocalDate;

public class DigitalKey {

    private String code;
    private int roomNumber;
    private LocalDate expirationDate;

    public DigitalKey() {
    }

    public DigitalKey(String code, int roomNumber, LocalDate expirationDate) {
        this.code = code;
        this.roomNumber = roomNumber;
        this.expirationDate = expirationDate;
    }

    // --- Getters & Setters ---

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
