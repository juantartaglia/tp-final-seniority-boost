package com.fourkbsys.eventms.domain.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventGateway {

    List<Event> findEventsWithCriteria(String criteria);
    List<Event> findAllEventsFromDate(LocalDateTime dateTime);
    Optional<Event> findById(Long id);
    Event saveEvent(Event event);

    boolean isOverlapping(Event event);

}
