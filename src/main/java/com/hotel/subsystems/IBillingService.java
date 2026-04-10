package com.hotel.subsystems;

import com.hotel.model.Invoice;
import com.hotel.model.Reservation;

public interface IBillingService {
    Invoice generateInvoice(Reservation reservation);
}
