package com.fourkbsys.eventms.data;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "event")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long eventId;

    @NotBlank String name;

    String description;

    @NotNull LocalDateTime fromDate;

    @NotNull LocalDateTime toDate;

    @ManyToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name = "roomId")
    RoomEntity room;

    @OneToMany(mappedBy = "ticketId", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Set<TicketEntity> tickets = new HashSet<>();

    String state;

    String type;

    public EventEntity(String name, LocalDateTime fromDate, LocalDateTime toDate, RoomEntity room) {
            this.name = name;
            this.fromDate = fromDate;
            this.toDate = toDate;
    }

}
