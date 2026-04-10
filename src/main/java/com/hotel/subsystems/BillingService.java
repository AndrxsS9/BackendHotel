package com.hotel.subsystems;

import com.hotel.enums.TypeService;
import com.hotel.model.Invoice;
import com.hotel.model.Reservation;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BillingService implements IBillingService {

    @Override
    public Invoice generateInvoice(Reservation reservation) {
        long nights = ChronoUnit.DAYS.between(
                reservation.getCheckInDate(), reservation.getCheckOutDate());

        double roomCharge = reservation.getTotalPrice();

        Map<String, Double> serviceBreakdown = new LinkedHashMap<>();
        double servicesTotal = 0.0;

        for (TypeService service : reservation.getAdditionalServices()) {
            double cost = service.getBasePrice();
            if (service.isPerNight()) {
                cost = cost * nights;
            }

            serviceBreakdown.merge(service.name(), cost, Double::sum);
            servicesTotal += cost;
        }

        String invoiceId = "INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return new Invoice(invoiceId, reservation.getId(), roomCharge, servicesTotal, serviceBreakdown);
    }
}
