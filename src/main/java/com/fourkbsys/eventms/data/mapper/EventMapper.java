package com.fourkbsys.eventms.data.mapper;

import com.fourkbsys.eventms.data.EventEntity;
import com.fourkbsys.eventms.domain.Event.Event;
import com.fourkbsys.eventms.domain.Event.EventState;
import com.fourkbsys.eventms.domain.Event.EventType;
import com.fourkbsys.eventms.domain.Ticket.Ticket;

import java.util.*;

public abstract class EventMapper {

    public static Event toModel(EventEntity eventEntity) {
       Event event=  Event.builder().eventId(eventEntity.getEventId())
               .description(eventEntity.getDescription())
               .name(eventEntity.getName())
               .fromDate(eventEntity.getFromDate())
               .toDate(eventEntity.getToDate())
               .room(RoomMapper.toModel(eventEntity.getRoom()))
               .build();
               List<Ticket> tickets = eventEntity.getTickets() != null ? eventEntity.getTickets().stream().map(TicketMapper::toTicket).toList() : new ArrayList<>();;
               event.setTickets(tickets);
               event.setState(EventState.of(eventEntity.getState()));
               event.setType(EventType.of(eventEntity.getType()));

               return event;
    }

    public static EventEntity toEntity(Event event) {

        return EventEntity.builder()
                .eventId(event.getEventId())
                .description(event.getDescription())
                .name(event.getName())
                .fromDate(event.getFromDate())
                .toDate(event.getToDate())
                .room(RoomMapper.toEntity(event.getRoom()))
                .state(event.getState().getState())
                .type(event.getType().getType())
                .build();
    }
}
