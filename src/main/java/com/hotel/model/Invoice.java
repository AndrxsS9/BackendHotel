package com.hotel.model;

import java.util.Map;

public class Invoice {

    private String id;
    private String reservationId;
    private double roomCharge;
    private double servicesCharge;
    private double subtotal;
    private double tax;
    private double total;
    private Map<String, Double> serviceBreakdown;

    public Invoice() {
    }

    public Invoice(String id, String reservationId, double roomCharge,
                   double servicesCharge, Map<String, Double> serviceBreakdown) {
        this.id = id;
        this.reservationId = reservationId;
        this.roomCharge = roomCharge;
        this.servicesCharge = servicesCharge;
        this.serviceBreakdown = serviceBreakdown;
        this.subtotal = roomCharge + servicesCharge;
        this.tax = this.subtotal * 0.19;
        this.total = this.subtotal + this.tax;
    }

    // --- Getters & Setters ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public double getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(double roomCharge) {
        this.roomCharge = roomCharge;
    }

    public double getServicesCharge() {
        return servicesCharge;
    }

    public void setServicesCharge(double servicesCharge) {
        this.servicesCharge = servicesCharge;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<String, Double> getServiceBreakdown() {
        return serviceBreakdown;
    }

    public void setServiceBreakdown(Map<String, Double> serviceBreakdown) {
        this.serviceBreakdown = serviceBreakdown;
    }
}
