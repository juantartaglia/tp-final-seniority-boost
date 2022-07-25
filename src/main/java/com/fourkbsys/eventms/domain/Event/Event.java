package com.fourkbsys.eventms.domain.Event;

import com.fourkbsys.eventms.domain.Room.Room;
import com.fourkbsys.eventms.domain.Ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    private Long eventId;
    private String name;
    private String description;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Room room;
    private List<Ticket> tickets;
    private EventState state;
    private EventType type;

}
