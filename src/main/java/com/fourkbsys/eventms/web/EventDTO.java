package com.fourkbsys.eventms.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class EventDTO {

    String eventId;
    @NotBlank
    String name;
    @NotBlank
    String description;
    @NotNull
    LocalDateTime fromDate;
    @NotNull
    LocalDateTime toDate;
    @NotBlank
    String roomId;

    Integer totalTickets;
    Integer availableTickets;
    Integer bookedTickets;
}
