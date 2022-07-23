package com.fourkbsys.eventms.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    Optional<Room> getById(long roomId);
    List<Room> getAll();
    Room createRoom(Room room);

}
