package com.hotel.subsystems;

import com.hotel.cache.InMemoryStore;
import com.hotel.enums.TypeRoom;
import com.hotel.model.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {

    private final InMemoryStore store;

    public RoomService(InMemoryStore store) {
        this.store = store;
    }

    @Override
    public List<Room> checkAvailability(LocalDate checkIn, LocalDate checkOut, TypeRoom type) {
        return store.getRooms().values().stream()
                .filter(room -> room.isAvailable())
                .filter(room -> type == null || room.getType() == type)
                .collect(Collectors.toList());
    }

    @Override
    public Room bookRoom(String roomId) {
        Room room = store.getRooms().get(roomId);
        if (room == null) {
            throw new RuntimeException("Room not found: " + roomId);
        }
        if (!room.isAvailable()) {
            throw new RuntimeException("Room is not available: " + roomId);
        }
        room.setAvailable(false);
        return room;
    }

    @Override
    public void releaseRoom(String roomId) {
        Room room = store.getRooms().get(roomId);
        if (room != null) {
            room.setAvailable(true);
        }
    }
}
