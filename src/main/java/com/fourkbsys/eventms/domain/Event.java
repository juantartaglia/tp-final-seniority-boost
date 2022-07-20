package com.fourkbsys.eventms.domain;

import java.time.LocalDateTime;

public class Event {
    String eventId;
    String name;
    String description;
    LocalDateTime fromDate;
    LocalDateTime toDate;
    String roomId;
    Integer totalTickets;
    Integer availableTickets;
    Integer bookedTickets;
}
