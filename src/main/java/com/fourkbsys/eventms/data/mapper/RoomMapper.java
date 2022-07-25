package com.fourkbsys.eventms.data.mapper;

import com.fourkbsys.eventms.data.RoomEntity;
import com.fourkbsys.eventms.domain.Room.Room;
import com.fourkbsys.eventms.domain.Room.RoomState;

public abstract class RoomMapper {
    public static RoomEntity toEntity(Room r) {
        RoomEntity roomEntity = RoomEntity.builder()
                .description(r.getDescription())
                .name(r.getName())
                .location(r.getLocation())
                .maxCapacity(r.getMaxCapacity())
                .state(r.getState().getState()).build();

        if (r.getRoomId() != null) roomEntity.setRoomId(r.getRoomId());

        return roomEntity;
    }


    public static Room toModel(RoomEntity r) {
        return Room.builder()
                .description(r.getDescription())
                .name(r.getName())
                .location(r.getLocation())
                .maxCapacity(r.getMaxCapacity())
                .roomId(r.getRoomId())
                .state(RoomState.of(r.getState())).build();
    }
}
