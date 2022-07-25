package com.fourkbsys.eventms.domain.Assistant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assistant {
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
