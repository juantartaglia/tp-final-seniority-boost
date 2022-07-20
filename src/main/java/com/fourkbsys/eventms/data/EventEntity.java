package com.fourkbsys.eventms.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "event")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String eventId;

    @NotBlank String name;

    String description;

    @NotNull LocalDateTime fromDate;

    @NotNull LocalDateTime toDate;

    @ManyToOne(optional=false, fetch=FetchType.EAGER)
    @JoinColumn(name="roomId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    RoomEntity room;

}
