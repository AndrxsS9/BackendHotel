package com.hotel.subsystems;

import com.hotel.model.DigitalKey;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class AccessService implements IAccessService {

    @Override
    public DigitalKey generateKey(int roomNumber, LocalDate expirationDate) {
        String code = UUID.randomUUID().toString();
        return new DigitalKey(code, roomNumber, expirationDate);
    }
}
