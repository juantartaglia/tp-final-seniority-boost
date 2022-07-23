package com.fourkbsys.eventms.web.mapper;


import com.fourkbsys.eventms.domain.Room.Room;
import com.fourkbsys.eventms.domain.Room.RoomStatus;
import com.fourkbsys.eventms.web.dto.RoomDTO;

public abstract class RoomMapper {

    public static Room toRoom(RoomDTO roomDTO){
        Room room = new Room();
        room.setRoomId(roomDTO.getRoomId());
        room.setDescription(roomDTO.getDescription());
        room.setName(roomDTO.getName());
        room.setMaxCapacity(roomDTO.getMaxCapacity());
        room.setLocation(roomDTO.getLocation());
        room.setStatus(RoomStatus.of(roomDTO.getStatus()));
        return room;
    }

    public static RoomDTO toRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId() != null ? room.getRoomId() : null);
        roomDTO.setDescription(room.getDescription());
        roomDTO.setName(room.getName());
        roomDTO.setMaxCapacity(room.getMaxCapacity());
        roomDTO.setLocation(room.getLocation());
        roomDTO.setStatus(room.getStatus().getStatus());
        return roomDTO;
    }
}
