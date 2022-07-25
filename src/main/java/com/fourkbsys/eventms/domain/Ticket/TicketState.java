package com.fourkbsys.eventms.domain.Ticket;

import java.util.Objects;
import java.util.stream.Stream;

public enum TicketState {

    ENABLED("enabled"),
    DISABLED("disabled"),
    BOOKED("booked"),
    AVAILABLE("available"),
    CANCELED("canceled");
    private String state;

    private TicketState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public static TicketState of(String state) {
        if (state == null) {
            return null;
        }
        return Stream.of(TicketState.values())
                .filter(p -> Objects.equals(p.getState(), state))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
