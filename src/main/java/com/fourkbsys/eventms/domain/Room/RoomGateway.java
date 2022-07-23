package com.fourkbsys.eventms.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomGateway {
    Optional<Room> getRoomById(long id);
    List<Room> getAll();
    Room saveRoom(Room room);

}
