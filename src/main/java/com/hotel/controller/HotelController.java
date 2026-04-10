package com.hotel.controller;

import com.hotel.facade.HotelFacade;
import com.hotel.model.Invoice;
import com.hotel.model.Reservation;
import com.hotel.model.ReservationDTO;
import com.hotel.model.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller - ONLY communicates with HotelFacade.
 * NEVER calls subsystems directly (Facade Pattern rule).
 */
@RestController
@RequestMapping("/api/hotel")
@CrossOrigin("*")
public class HotelController {

    private final HotelFacade facade;

    public HotelController(HotelFacade facade) {
        this.facade = facade;
    }

    // POST /api/hotel/reserve
    @PostMapping("/reserve")
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO dto) {
        try {
            Reservation reservation = facade.createReservation(dto);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/hotel/availability?checkIn=&checkOut=&type=
    @GetMapping("/availability")
    public ResponseEntity<?> checkAvailability(
            @RequestParam String checkIn,
            @RequestParam String checkOut,
            @RequestParam(required = false) String type) {
        try {
            List<Room> rooms = facade.checkAvailability(checkIn, checkOut, type);
            return ResponseEntity.ok(rooms);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // POST /api/hotel/services/{reservationId}
    @PostMapping("/services/{reservationId}")
    public ResponseEntity<?> addService(
            @PathVariable String reservationId,
            @RequestBody Map<String, String> body) {
        try {
            String serviceType = body.get("serviceType");
            Reservation reservation = facade.addService(reservationId, serviceType);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // PUT /api/hotel/checkin/{reservationId}
    @PutMapping("/checkin/{reservationId}")
    public ResponseEntity<?> checkIn(@PathVariable String reservationId) {
        try {
            Reservation reservation = facade.performCheckIn(reservationId);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // PUT /api/hotel/checkout/{reservationId}
    @PutMapping("/checkout/{reservationId}")
    public ResponseEntity<?> checkOut(@PathVariable String reservationId) {
        try {
            Invoice invoice = facade.performCheckOut(reservationId);
            return ResponseEntity.ok(invoice);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // GET /api/hotel/reservation/{reservationId}
    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<?> getReservation(@PathVariable String reservationId) {
        try {
            Reservation reservation = facade.getReservation(reservationId);
            return ResponseEntity.ok(reservation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
