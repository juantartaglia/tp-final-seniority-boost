package com.fourkbsys.eventms.domain.Ticket;

import com.fourkbsys.eventms.domain.Assistant.Assistant;
import com.fourkbsys.eventms.domain.Event.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    private Long ticketId;

    @NotNull
    private Assistant assistant;

    @Enumerated(EnumType.STRING)
    private TicketState state;

    @NotNull
    private Event event;

}
