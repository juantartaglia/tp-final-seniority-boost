package com.fourkbsys.eventms.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {

    private Long roomId;

    @NotNull(message = "name could not be null")
    @NotBlank(message = "name could not be empty")
    private String name;

    @NotNull(message = "description could not be null")
    @NotBlank(message = "description could not be empty")
    private String description;

    @NotNull(message = "location could not be null")
    @NotBlank(message = "location could not be empty")
    private String location;

    @NotNull(message = "maxCapacity could not be null")
    @Positive(message = "maxCapacity must be greater than 0")
    private Integer maxCapacity;

    private String state;
}
