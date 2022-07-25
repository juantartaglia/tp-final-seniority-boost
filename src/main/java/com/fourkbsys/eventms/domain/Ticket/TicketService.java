package com.fourkbsys.eventms.domain.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getTicketListByEventId(Long eventId);

    Ticket createTicket(Ticket ticket);
}
