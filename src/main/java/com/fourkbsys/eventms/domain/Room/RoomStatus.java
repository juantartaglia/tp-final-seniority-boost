package com.fourkbsys.eventms.domain.Room;

import java.util.Objects;
import java.util.stream.Stream;

public enum RoomStatus {

        ENABLED("enabled"),DISABLED("disabled");
        private String status;

        private RoomStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public static RoomStatus of(String status) {
            if (status == null) {
                return null;
            }
            return Stream.of(RoomStatus.values())
                    .filter(p -> Objects.equals(p.getStatus(), status))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
}
