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

    private Long roomId;
    @NotBlank
    private String name;

    private String description;
    @NotBlank
    private String location;
    @Positive
    private Integer maxCapacity;

    @Enumerated(EnumType.STRING)
    private RoomState state;

}
