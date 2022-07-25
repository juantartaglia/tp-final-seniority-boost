package com.fourkbsys.eventms.domain.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAllWithCriteria(String criteria);
    Optional<Event> findByEventId(Long eventId);

    Event createEvent(Event event);

    Event updateEvent(Event event);

    void deleteEvent(Long eventId);
}
