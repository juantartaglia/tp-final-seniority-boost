package com.fourkbsys.eventms.data;

import com.fourkbsys.eventms.data.mapper.RoomMapper;
import com.fourkbsys.eventms.domain.Room.Room;
import com.fourkbsys.eventms.domain.Room.RoomGateway;
import com.fourkbsys.eventms.domain.Room.RoomState;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RoomGatewayImpl implements RoomGateway {

    private final RoomPostgresqlRepository roomRepository;

    public RoomGatewayImpl(RoomPostgresqlRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<Room> getRoomById(long id) {
        return roomRepository.findById(id).map(this::toRoom);
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll().stream().map(this::toRoom).toList();
    }

    @Override
    public Room saveRoom(Room room) {
        return toRoom(roomRepository.save(RoomMapper.toEntity(room)));
    }

    private Room toRoom(RoomEntity re) {
        return new Room(re.getRoomId(), re.getName(), re.getDescription(), re.getLocation(), re.getMaxCapacity(), RoomState.of(re.getState()));
    }
}
