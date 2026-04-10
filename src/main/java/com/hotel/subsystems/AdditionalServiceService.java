package com.hotel.subsystems;

import com.hotel.cache.InMemoryStore;
import com.hotel.enums.TypeService;
import com.hotel.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class AdditionalServiceService implements IAdditionalServiceService {

    private final InMemoryStore store;

    public AdditionalServiceService(InMemoryStore store) {
        this.store = store;
    }

    @Override
    public double addService(String reservationId, TypeService serviceType) {
        Reservation reservation = store.getReservations().get(reservationId);
        if (reservation == null) {
            throw new RuntimeException("Reservation not found: " + reservationId);
        }

        reservation.getAdditionalServices().add(serviceType);

        // Calculate the cost of this service
        double cost = serviceType.getBasePrice();
        if (serviceType.isPerNight()) {
            long nights = ChronoUnit.DAYS.between(
                    reservation.getCheckInDate(), reservation.getCheckOutDate());
            cost = cost * nights;
        }

        return cost;
    }
}
