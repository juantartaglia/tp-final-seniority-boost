package com.fourkbsys.eventms.web;

import com.fourkbsys.eventms.domain.Event.EventService;
import com.fourkbsys.eventms.domain.Room.Room;
import com.fourkbsys.eventms.domain.Room.RoomService;
import com.fourkbsys.eventms.domain.Ticket.Ticket;
import com.fourkbsys.eventms.domain.Ticket.TicketService;
import com.fourkbsys.eventms.web.dto.EventDTO;
import com.fourkbsys.eventms.web.mapper.EventMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class EventController {

    private final EventService eventService;
    private final RoomService roomService;
    private final TicketService ticketService;

    public EventController(EventService eventService, RoomService roomService, TicketService ticketService) {
        this.eventService = eventService;
        this.roomService = roomService;
        this.ticketService = ticketService;
    }

    @GetMapping(value= "/events",produces = "application/json")
    public List<EventDTO> getAllEventsByDate(@RequestParam(name= "date") Optional<String> date){
        return eventService.findAllWithCriteria(date.orElse("")).stream().map(EventMapper::toEventDTO).toList();
    }

    @PostMapping(value= "/events",produces = "application/json")
    public ResponseEntity<EventDTO> createEvent(@RequestBody @Validated EventDTO eventDTO) {

        Optional<Room> roomOpt = roomService.getById(eventDTO.getRoomId());
        if (roomOpt.isEmpty()){
            throw new IllegalArgumentException("roomId not found");
        }

        return Stream.of(EventMapper.toEvent(eventDTO, roomOpt.get(), new ArrayList<>()))
                    .map(eventService::createEvent)
                    .map(EventMapper::toEventDTO)
                    .map(e -> new ResponseEntity<EventDTO>(e, HttpStatus.CREATED))
                    .findFirst()
                    .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PutMapping(value= "/events/{id}", produces = "application/json")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable("id") Long id ,@RequestBody @Validated EventDTO eventDTO) {

        Optional<Room> roomOpt = roomService.getById(eventDTO.getRoomId());
        if (roomOpt.isEmpty()){
            throw new IllegalArgumentException("roomId not found");
        }

        List<Ticket> ticketList = ticketService.getTicketListByEventId(eventDTO.getEventId());

        return Stream.of(EventMapper.toEvent(eventDTO, roomOpt.get(), ticketList))
                .map(eventService::updateEvent)
                .map(EventMapper::toEventDTO)
                .map(e -> new ResponseEntity<EventDTO>(e, HttpStatus.OK))
                .findFirst()
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @DeleteMapping(value= "/events/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteEvent(@PathVariable("id") Long id) {
        eventService.deleteEvent(id);
    }

}
