package com.hotel.cache;

import com.hotel.enums.TypeRoom;
import com.hotel.model.Reservation;
import com.hotel.model.Room;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryStore {

    private final ConcurrentHashMap<String, Room> rooms = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Reservation> reservations = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        TypeRoom[] types = {
                TypeRoom.SIMPLE, TypeRoom.SIMPLE, TypeRoom.SIMPLE, TypeRoom.SIMPLE,
                TypeRoom.SHARED, TypeRoom.SHARED, TypeRoom.SHARED,
                TypeRoom.PRIVATE, TypeRoom.PRIVATE,
                TypeRoom.LUXURY, TypeRoom.LUXURY,
                TypeRoom.SUITE, TypeRoom.SUITE,
                TypeRoom.PENTHOUSE, TypeRoom.PENTHOUSE
        };

        for (int i = 0; i < types.length; i++) {
            int roomNumber = 100 + (i + 1);
            String id = "ROOM-" + roomNumber;
            Room room = new Room(id, roomNumber, types[i]);
            rooms.put(id, room);
        }
    }

    public ConcurrentHashMap<String, Room> getRooms() {
        return rooms;
    }

    public ConcurrentHashMap<String, Reservation> getReservations() {
        return reservations;
    }
}
