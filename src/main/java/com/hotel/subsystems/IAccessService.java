package com.hotel.subsystems;

import com.hotel.model.DigitalKey;

import java.time.LocalDate;

public interface IAccessService {
    DigitalKey generateKey(int roomNumber, LocalDate expirationDate);
}
