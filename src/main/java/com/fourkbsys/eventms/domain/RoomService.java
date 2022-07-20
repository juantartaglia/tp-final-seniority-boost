package com.fourkbsys.eventms.domain;

import java.util.List;

public interface RoomService {

    Room getById(String roomId);
    List<Room> getAll();

}
