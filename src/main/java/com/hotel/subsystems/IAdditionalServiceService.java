package com.hotel.subsystems;

import com.hotel.enums.TypeService;

public interface IAdditionalServiceService {
    double addService(String reservationId, TypeService serviceType);
}
