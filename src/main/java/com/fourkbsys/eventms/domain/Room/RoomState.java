package com.fourkbsys.eventms.domain.Room;

import java.util.Objects;
import java.util.stream.Stream;

public enum RoomState {

        ENABLED("enabled"),DISABLED("disabled");
        private String state;

        private RoomState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public static RoomState of(String state) {
            if (state == null) {
                return null;
            }
            return Stream.of(RoomState.values())
                    .filter(p -> Objects.equals(p.getState(), state))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
}
