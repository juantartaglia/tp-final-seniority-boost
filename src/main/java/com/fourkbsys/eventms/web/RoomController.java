package com.fourkbsys.eventms.web;

import com.fourkbsys.eventms.domain.Room.Room;
import com.fourkbsys.eventms.domain.Room.RoomService;
import com.fourkbsys.eventms.web.dto.RoomDTO;
import com.fourkbsys.eventms.web.mapper.RoomMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<RoomDTO>> getRoomList() {
        return new ResponseEntity<>(roomService.getAll().stream().map(RoomMapper::toRoomDTO).toList(), HttpStatus.OK);
    }

    @GetMapping(value = "/{roomId}", produces = "application/json")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable @Validated @Positive long roomId) {
        return roomService.getById(roomId)
                .map(RoomMapper::toRoomDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<RoomDTO> create(@RequestBody @Validated RoomDTO roomDTO) {
        roomDTO.setStatus("enabled");
        Room room = roomService.createRoom(RoomMapper.toRoom(roomDTO));
        return new ResponseEntity<>(RoomMapper.toRoomDTO(room),
                                    HttpStatus.CREATED);
    }
}
