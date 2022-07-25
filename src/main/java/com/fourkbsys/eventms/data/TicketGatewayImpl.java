package com.fourkbsys.eventms.data;

import com.fourkbsys.eventms.data.mapper.EventMapper;
import com.fourkbsys.eventms.data.mapper.TicketMapper;
import com.fourkbsys.eventms.domain.Event.Event;
import com.fourkbsys.eventms.domain.Ticket.Ticket;
import com.fourkbsys.eventms.domain.Ticket.TicketGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketGatewayImpl implements TicketGateway {

    private final TicketPostgresqlRepository ticketRepository;

    public TicketGatewayImpl(TicketPostgresqlRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public List<Ticket> getTicketByEvent(Event event) {
        return ticketRepository.findByEventEntityId(EventMapper.toEntity(event)).stream().map(TicketMapper::toTicket).toList();
    }

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return TicketMapper.toTicket(ticketRepository.save(TicketMapper.toTicketEntity(ticket)));
    }
}
