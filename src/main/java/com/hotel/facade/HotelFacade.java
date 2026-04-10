package com.hotel.facade;

import com.hotel.cache.InMemoryStore;
import com.hotel.enums.ReservationStatus;
import com.hotel.enums.TypeRoom;
import com.hotel.enums.TypeService;
import com.hotel.model.*;
import com.hotel.subsystems.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class HotelFacade {

    private final IRoomService roomService;
    private final IRateService rateService;
    private final IAdditionalServiceService additionalServiceService;
    private final IBillingService billingService;
    private final IAccessService accessService;
    private final InMemoryStore store;

    public HotelFacade(IRoomService roomService,
            IRateService rateService,
            IAdditionalServiceService additionalServiceService,
            IBillingService billingService,
            IAccessService accessService,
            InMemoryStore store) {
        this.roomService = roomService;
        this.rateService = rateService;
        this.additionalServiceService = additionalServiceService;
        this.billingService = billingService;
        this.accessService = accessService;
        this.store = store;
    }

    public Reservation createReservation(ReservationDTO dto) {
        LocalDate checkIn = LocalDate.parse(dto.getCheckInDate());
        LocalDate checkOut = LocalDate.parse(dto.getCheckOutDate());

        List<Room> available = roomService.checkAvailability(checkIn, checkOut, dto.getRoomType());
        if (available.isEmpty()) {
            throw new RuntimeException("No rooms available for type: " + dto.getRoomType());
        }
        Room room = roomService.bookRoom(available.get(0).getId());
        double totalPrice = rateService.calculatePrice(room.getType(), checkIn, checkOut);
        Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID().toString());
        reservation.setRoom(room);
        reservation.setGuestName(dto.getGuestName());
        reservation.setGuestLastName(dto.getGuestLastName());
        reservation.setGuestEmail(dto.getGuestEmail());
        reservation.setGuestPhone(dto.getGuestPhone());
        reservation.setCheckInDate(checkIn);
        reservation.setCheckOutDate(checkOut);
        reservation.setSeason(rateService.determineSeason(checkIn));
        reservation.setTotalPrice(totalPrice);
        reservation.setStatus(ReservationStatus.PENDING);

        store.getReservations().put(reservation.getId(), reservation);

        return reservation;
    }

    public Reservation addService(String reservationId, String serviceType) {
        TypeService type = TypeService.valueOf(serviceType.toUpperCase());
        additionalServiceService.addService(reservationId, type);

        Reservation reservation = store.getReservations().get(reservationId);
        if (reservation == null) {
            throw new RuntimeException("Reservation not found: " + reservationId);
        }
        return reservation;
    }

    public Reservation performCheckIn(String reservationId) {
        Reservation reservation = store.getReservations().get(reservationId);
        if (reservation == null) {
            throw new RuntimeException("Reservation not found: " + reservationId);
        }
        if (reservation.getStatus() != ReservationStatus.PENDING) {
            throw new RuntimeException("Reservation is not in PENDING status");
        }

        // Generate digital key
        DigitalKey key = accessService.generateKey(
                reservation.getRoom().getNumber(),
                reservation.getCheckOutDate());
        reservation.setKey(key);
        reservation.setStatus(ReservationStatus.ACTIVE);

        return reservation;
    }

    public Invoice performCheckOut(String reservationId) {
        Reservation reservation = store.getReservations().get(reservationId);
        if (reservation == null) {
            throw new RuntimeException("Reservation not found: " + reservationId);
        }
        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new RuntimeException("Reservation is not in ACTIVE status");
        }

        Invoice invoice = billingService.generateInvoice(reservation);
        roomService.releaseRoom(reservation.getRoom().getId());
        reservation.setStatus(ReservationStatus.COMPLETED);

        return invoice;
    }

    public List<Room> checkAvailability(String checkIn, String checkOut, String type) {
        LocalDate in = LocalDate.parse(checkIn);
        LocalDate out = LocalDate.parse(checkOut);
        TypeRoom roomType = (type != null && !type.isEmpty()) ? TypeRoom.valueOf(type.toUpperCase()) : null;
        return roomService.checkAvailability(in, out, roomType);
    }

    public Reservation getReservation(String reservationId) {
        Reservation reservation = store.getReservations().get(reservationId);
        if (reservation == null) {
            throw new RuntimeException("Reservation not found: " + reservationId);
        }
        return reservation;
    }
}
