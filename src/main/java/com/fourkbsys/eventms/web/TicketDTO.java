package com.fourkbsys.eventms.web;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class TicketDTO {
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

}
