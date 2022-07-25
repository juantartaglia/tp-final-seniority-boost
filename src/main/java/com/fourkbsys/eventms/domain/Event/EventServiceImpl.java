package com.fourkbsys.eventms.domain.Event;

import com.fourkbsys.eventms.commons.DateStringValidator;
import com.fourkbsys.eventms.domain.Room.Room;
import com.fourkbsys.eventms.domain.Room.RoomGateway;
import com.fourkbsys.eventms.domain.Ticket.TicketGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {

    private final String[] actionAllowed = {"", "today", "tomorrow", "past"};

    private final EventGateway eventGateway;
    private final RoomGateway roomGateway;
    private final TicketGateway ticketsGateway;

    public EventServiceImpl(EventGateway eventGateway, RoomGateway roomGateway, TicketGateway ticketsGateway) {
        this.eventGateway = eventGateway;
        this.roomGateway = roomGateway;
        this.ticketsGateway = ticketsGateway;
    }

    @Override
    public List<Event> findAllWithCriteria(String criteria) {

        if (!checkActionAllowed(criteria)){
            throw new IllegalArgumentException(String.format("date param values allowed {} or valid date value", actionAllowed.toString()));
        }
        return eventGateway.findEventsWithCriteria(criteria);
    }

    @Override
    public Optional<Event> findByEventId(Long eventId) {
        return eventGateway.findById(eventId);
    }

    @Override
    public Event createEvent(Event event) {
        Optional<Room> roomOpt = roomGateway.getRoomById(event.getRoom().getRoomId());
        if (roomOpt.isEmpty()) {
            throw new RuntimeException("roomId not found");
        }
        if(Boolean.TRUE.equals(eventGateway.isOverlapping(event))) {
            throw new RuntimeException("Room not available");
        }
        event.setState(EventState.ENABLED);
        event.setTickets(new ArrayList<>());
        return eventGateway.saveEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        if (roomGateway.getRoomById(event.getRoom().getRoomId()).isEmpty()) {
            throw new RuntimeException("roomId not found");
        }
        Optional<Event> eventOptional = eventGateway.findById(event.getEventId());
        if(eventOptional.isEmpty()) {
            throw new RuntimeException("Event not found");
        }
        if (event.getFromDate().isBefore(LocalDateTime.now()) || event.getToDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Event is readonly mode");
        }

        Event eventToUpdate = eventOptional.get();
        eventToUpdate.setToDate(event.getToDate());
        eventToUpdate.setFromDate(event.getFromDate());
        eventToUpdate.setName(event.getName());
        eventToUpdate.setDescription(event.getDescription());
        eventToUpdate.setRoom(event.getRoom());

        return eventGateway.saveEvent(eventToUpdate);
    }

    @Override
    public void deleteEvent(Long eventId) {
        Optional<Event> eventOpt = eventGateway.findById(eventId);

        if (eventOpt.isEmpty()) {
            throw new IllegalArgumentException("Event not found");
        }

        Event event = eventOpt.get();

        switch (event.getState()) {
            case DELETED -> throw new IllegalArgumentException("Event already is deleted");
            case DISABLED -> throw new IllegalArgumentException("Event is read-only mode");
        }
        if (!event.getTickets().isEmpty()) {
            throw new IllegalArgumentException("Event has tickets");
        }
        if (event.getFromDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Event is read-only mode");
        }
        event.setState(EventState.DELETED);
        eventGateway.saveEvent(event);
    }

    private boolean checkActionAllowed(String criteria){

        if (Arrays.stream(actionAllowed).noneMatch(criteria::equalsIgnoreCase)) {
            return true;
        } else if (DateStringValidator.isValid(criteria)) {
            return true;
        }
        return false;
    }
}
