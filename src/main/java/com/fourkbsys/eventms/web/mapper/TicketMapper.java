package com.fourkbsys.eventms.web.mapper;

import com.fourkbsys.eventms.domain.Event.Event;
import com.fourkbsys.eventms.domain.Ticket.Ticket;
import com.fourkbsys.eventms.domain.Ticket.TicketState;
import com.fourkbsys.eventms.web.dto.TicketDTO;

public abstract class TicketMapper {

    public static Ticket toTicket(TicketDTO ticketDTO, Event event){
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketDTO.getTicketId());
        ticket.setEvent(event);
        ticket.setState(TicketState.of(ticketDTO.getState()));
        ticket.setAssistant(AssistantMapper.toAssistant(ticketDTO.getAssistant()));
        return ticket;
    }

    public static TicketDTO toTicketDTO(Ticket ticket) {
        return TicketDTO.builder()
                .ticketId(ticket.getTicketId())
                .eventId(ticket.getEvent().getEventId())
                .state(ticket.getState().getState())
                .assistant(AssistantMapper.toAssistantDTO(ticket.getAssistant()))
                .build();
    }
}
