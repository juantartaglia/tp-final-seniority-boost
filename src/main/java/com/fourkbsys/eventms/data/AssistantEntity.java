package com.fourkbsys.eventms.data;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "assistant")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class AssistantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
