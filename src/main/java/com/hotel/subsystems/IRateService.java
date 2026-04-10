package com.hotel.subsystems;

import com.hotel.enums.TypeRoom;
import com.hotel.enums.Season;

import java.time.LocalDate;

public interface IRateService {
    double calculatePrice(TypeRoom roomType, LocalDate checkIn, LocalDate checkOut);
    Season determineSeason(LocalDate checkIn);
    long calculateNights(LocalDate checkIn, LocalDate checkOut);
}
