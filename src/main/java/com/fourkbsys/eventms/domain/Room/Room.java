package com.fourkbsys.eventms.domain.Room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    Long roomId;
    @NotBlank
    String name;

    String description;
    @NotBlank
    String location;
    @Positive
    Integer maxCapacity;

    @Enumerated(EnumType.STRING)
    RoomState state;

}
