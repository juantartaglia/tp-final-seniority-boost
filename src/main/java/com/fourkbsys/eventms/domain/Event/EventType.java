package com.fourkbsys.eventms.domain.Event;

import java.util.Objects;
import java.util.stream.Stream;

public enum EventType {

    FREE("free"),
    PAYMENT("payment");

    private String type;
    private EventType(String type) { this.type = type; }

    public String getType() { return type; }

    public static EventType of(String type){
        if(type == null) {
            return null;
        }
        return Stream.of(EventType.values())
                .filter(p -> Objects.equals(p.getType(), type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }

}
