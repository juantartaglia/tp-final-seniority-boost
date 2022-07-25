package com.fourkbsys.eventms.data;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ticketId;

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="assistantId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    AssistantEntity assistant;

    String state;

    @ManyToOne(optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name="eventId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    EventEntity event;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
