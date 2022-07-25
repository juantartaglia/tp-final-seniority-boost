package com.fourkbsys.eventms.web.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AssistantDTO {

    Long assistantId;
    @NotBlank
    String firstName;
    String lastName;
    @NotBlank
    String document;

    @NotBlank
    @Email
    String email;
}
