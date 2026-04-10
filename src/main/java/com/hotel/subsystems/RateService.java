package com.hotel.subsystems;

import com.hotel.enums.Season;
import com.hotel.enums.TypeRoom;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class RateService implements IRateService {

    @Override
    public double calculatePrice(TypeRoom roomType, LocalDate checkIn, LocalDate checkOut) {
        long nights = calculateNights(checkIn, checkOut);
        double basePrice = roomType.getBasePrice();
        Season season = determineSeason(checkIn);

        if (season == Season.HIGH) {
            return basePrice * 1.5 * nights;
        }
        return basePrice * nights;
    }

    @Override
    public Season determineSeason(LocalDate checkIn) {
        int month = checkIn.getMonthValue();
        // TEMPORDAS ALTAS (DICIEMBRE, ENERO, JULIO, AGOSTO)
        if (month == 12 || month == 1 || month == 7 || month == 8) {
            return Season.HIGH;
        }
        return Season.LOW;
    }

    @Override
    public long calculateNights(LocalDate checkIn, LocalDate checkOut) {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }
}
