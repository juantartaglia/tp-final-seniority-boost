package com.fourkbsys.eventms.data.mapper;

import com.fourkbsys.eventms.data.TicketEntity;
import com.fourkbsys.eventms.domain.Ticket.Ticket;
import com.fourkbsys.eventms.domain.Ticket.TicketState;

public abstract class TicketMapper {

    public static Ticket toTicket(TicketEntity ticketEntity){
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketEntity.getTicketId());
        ticket.setEvent(EventMapper.toModel(ticketEntity.getEvent()));
        ticket.setState(TicketState.of(ticketEntity.getState()));
        ticket.setAssistant(AssistantMapper.toAssistant(ticketEntity.getAssistant()));
        return ticket;
    }

    public static TicketEntity toTicketEntity(Ticket ticket) {
        return TicketEntity.builder()
                .ticketId(ticket.getTicketId())
                .event(EventMapper.toEntity(ticket.getEvent()))
                .state(ticket.getState().getState())
                .assistant(AssistantMapper.toAssistantEntity(ticket.getAssistant()))
                .build();
    }
}
