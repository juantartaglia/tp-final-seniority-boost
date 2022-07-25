package com.fourkbsys.eventms.web.mapper;

import com.fourkbsys.eventms.domain.Event.Event;
import com.fourkbsys.eventms.domain.Event.EventType;
import com.fourkbsys.eventms.domain.Room.Room;
import com.fourkbsys.eventms.domain.Ticket.Ticket;
import com.fourkbsys.eventms.web.dto.EventDTO;

import java.util.List;

public abstract class EventMapper {

    public static Event toEvent(EventDTO eventDTO,Room r, List<Ticket> tl){
        Event event = new Event();
        if(eventDTO.getEventId() != null)
            event.setEventId(eventDTO.getEventId());

        event.setDescription(eventDTO.getDescription());
        event.setName(eventDTO.getName());
        event.setFromDate(eventDTO.getFromDate());
        event.setToDate(eventDTO.getToDate());
        event.setType(EventType.of(eventDTO.getType()));
        event.setRoom(r);
        event.setTickets(tl);
        return event;
    }

    public static EventDTO toEventDTO(Event e){

        long soldTicketQty = e.getTickets().stream().filter(t -> {
            return !t.getState().name().equals("canceled");
        }).count();

        return EventDTO.builder()
                .eventId(e.getEventId())
                .name(e.getName())
                .description(e.getDescription())
                .fromDate(e.getFromDate())
                .toDate(e.getToDate())
                .roomId(e.getRoom().getRoomId())
                .type(e.getType().getType())
                .totalTickets(e.getRoom().getMaxCapacity())
                .availableTickets((int) (e.getRoom().getMaxCapacity() - soldTicketQty))
                .bookedTickets((int) soldTicketQty)
                .state(e.getState().getState())
                .build();
    }
}
