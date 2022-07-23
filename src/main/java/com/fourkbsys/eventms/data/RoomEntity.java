package com.fourkbsys.eventms.data;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "room")
@Builder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long roomId;

    @NotBlank
    String name;

    String description;

    String location;

    @Positive
    Integer maxCapacity;

    String status;

}
