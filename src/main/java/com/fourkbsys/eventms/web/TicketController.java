package com.fourkbsys.eventms.web;

import com.fourkbsys.eventms.domain.Event.Event;
import com.fourkbsys.eventms.domain.Event.EventService;
import com.fourkbsys.eventms.domain.Ticket.TicketService;
import com.fourkbsys.eventms.web.dto.TicketDTO;
import com.fourkbsys.eventms.web.mapper.TicketMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping(value ="/tickets")
public class TicketController {
    
    private final TicketService ticketService;
    private final EventService eventService;

    public TicketController(TicketService ticketService, EventService eventService) {
        this.ticketService = ticketService;
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody @Validated TicketDTO ticketDTO) {
        Optional<Event> eventOpt = eventService.findByEventId(ticketDTO.getEventId());
        if (eventOpt.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return Stream.of(TicketMapper.toTicket(ticketDTO, eventOpt.get()))
                .map(ticketService::createTicket)
                .map(TicketMapper::toTicketDTO)
                .map(e -> new ResponseEntity<TicketDTO>(e, HttpStatus.CREATED))
                .findFirst()
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
