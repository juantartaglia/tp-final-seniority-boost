package com.fourkbsys.eventms.data;

import com.fourkbsys.eventms.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
@Entity
@Table(name = "room")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String roomId;

    @NotBlank
    String name;

    String description;

    String location;

    @Positive
    Integer maxCapacity;

    String status;


    public Room toRoom(RoomEntity roomEntity){
        return new Room();
    }
}
