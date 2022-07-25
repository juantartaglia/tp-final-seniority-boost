package com.fourkbsys.eventms.web.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TicketDTO {

    Long ticketId;

    Long eventId;
    AssistantDTO assistant;

    String state;

}
