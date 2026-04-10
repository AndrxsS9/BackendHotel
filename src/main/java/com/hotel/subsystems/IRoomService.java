package com.hotel.subsystems;

import com.hotel.enums.TypeRoom;
import com.hotel.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IRoomService {
    List<Room> checkAvailability(LocalDate checkIn, LocalDate checkOut, TypeRoom type);
    Room bookRoom(String roomId);
    void releaseRoom(String roomId);
}
