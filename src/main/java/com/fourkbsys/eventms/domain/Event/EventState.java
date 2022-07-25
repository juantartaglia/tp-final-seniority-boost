package com.fourkbsys.eventms.domain.Event;

import java.util.Objects;
import java.util.stream.Stream;

public enum EventState {

    ENABLED("enabled"),
    DISABLED("disabled"),
    DELETED("deleted"),
    SOLD_OUT("sold_out");

    private String state;
    private EventState(String state) { this.state = state; }

    public String getState() { return state; }

    public static EventState of(String state){
        if(state == null) {
            return null;
        }
        return Stream.of(EventState.values())
                .filter(p -> Objects.equals(p.getState(), state))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
