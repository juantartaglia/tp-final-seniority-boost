package com.fourkbsys.eventms.domain.Room;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomGateway roomGateway;

    public RoomServiceImpl(RoomGateway roomGateway) {
        this.roomGateway = roomGateway;
    }

    @Override
    public Optional<Room> getById(long id) {
        return roomGateway.getRoomById(id);
    }

    @Override
    public List<Room> getAll() {
        return roomGateway.getAll();
    }

    @Override
    public Room createRoom(Room room) {
        room.setStatus(RoomStatus.ENABLED);
        return roomGateway.saveRoom(room);
    }
}
