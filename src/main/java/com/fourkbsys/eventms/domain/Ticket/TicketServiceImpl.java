package com.fourkbsys.eventms.domain.Ticket;

import com.fourkbsys.eventms.domain.Assistant.Assistant;
import com.fourkbsys.eventms.domain.Assistant.AssistantGateway;
import com.fourkbsys.eventms.domain.Event.Event;
import com.fourkbsys.eventms.domain.Event.EventGateway;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    private final TicketGateway ticketGateway;
    private final EventGateway eventGateway;
    private final AssistantGateway assistantGateway;

    public TicketServiceImpl(TicketGateway ticketGateway, EventGateway eventGateway, AssistantGateway assistantGateway) {
        this.ticketGateway = ticketGateway;
        this.eventGateway = eventGateway;
        this.assistantGateway = assistantGateway;
    }


    @Override
    public List<Ticket> getTicketListByEventId(Long eventId) {
        Optional<Event> eventOpt = eventGateway.findById(eventId);
        if(eventOpt.isEmpty()) {
            throw new RuntimeException("Event not found");
        }
        return ticketGateway.getTicketByEvent(eventOpt.get());
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        if (ticket.getEvent().getFromDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Event date is before today");
        }

        long ticketsQty = ticketGateway.getTicketByEvent(ticket.getEvent()).size();

        if (ticketsQty >= ticket.getEvent().getRoom().getMaxCapacity()){
            throw new IllegalArgumentException("Event is sold out");
        }
        Assistant assistant = assistantGateway.saveAssistant(ticket.getAssistant());
        ticket.setAssistant(assistant);
        return ticketGateway.saveTicket(ticket);
    }
}
