package com.fourkbsys.eventms.web;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class RoomDTO {

    String roomId;
    @NotBlank
    String name;
    @NotBlank
    String description;
    @NotBlank
    String location;
    @Positive
    Integer maxCapacity;
    String status;
}
