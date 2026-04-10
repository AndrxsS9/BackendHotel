package com.hotel.model;

import com.hotel.enums.ReservationStatus;
import com.hotel.enums.Season;
import com.hotel.enums.TypeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private String id;
    private Room room;
    private String guestName;
    private String guestLastName;
    private String guestEmail;
    private String guestPhone;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Season season;
    private List<TypeService> additionalServices;
    private DigitalKey key;
    private ReservationStatus status;
    private double totalPrice;

    public Reservation() {
        this.additionalServices = new ArrayList<>();
        this.status = ReservationStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestLastName() {
        return guestLastName;
    }

    public void setGuestLastName(String guestLastName) {
        this.guestLastName = guestLastName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<TypeService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(List<TypeService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public DigitalKey getKey() {
        return key;
    }

    public void setKey(DigitalKey key) {
        this.key = key;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
