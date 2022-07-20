package com.fourkbsys.eventms.data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String ticketId;

    @NotBlank
    String firstName;
    String lastName;

    @NotBlank
    String document;

    @NotBlank
    @Email
    String email;

    String status;

    @ManyToOne(optional=false, fetch= FetchType.EAGER)
    @JoinColumn(name="eventId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    EventEntity event;

}
