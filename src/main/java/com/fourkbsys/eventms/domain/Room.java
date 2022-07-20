package com.fourkbsys.eventms.domain;

import lombok.Data;

@Data
public class Room {
    String roomId;
    String name;
    String description;
    String location;
    Integer maxCapacity;
    String status;
}
