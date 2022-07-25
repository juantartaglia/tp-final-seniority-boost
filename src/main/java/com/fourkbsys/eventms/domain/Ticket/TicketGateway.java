package com.fourkbsys.eventms.domain.Ticket;

import com.fourkbsys.eventms.domain.Event.Event;

import java.util.List;

public interface TicketGateway {

    List<Ticket> getTicketByEvent(Event event);

    Ticket saveTicket(Ticket ticket);

}
