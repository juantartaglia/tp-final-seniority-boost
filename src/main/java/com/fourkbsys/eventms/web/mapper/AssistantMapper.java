package com.fourkbsys.eventms.web.mapper;

import com.fourkbsys.eventms.domain.Assistant.Assistant;
import com.fourkbsys.eventms.web.dto.AssistantDTO;

public abstract class AssistantMapper {

    public static Assistant toAssistant(AssistantDTO assistantDTO){
        Assistant assistant = new Assistant();
        assistant.setAssistantId(assistantDTO.getAssistantId());
        assistant.setDocument(assistantDTO.getDocument());
        assistant.setEmail(assistantDTO.getEmail());
        assistant.setFirstName(assistantDTO.getFirstName());
        assistant.setLastName(assistantDTO.getLastName());
        return assistant;
    }

    public static AssistantDTO toAssistantDTO(Assistant assistant) {
        return AssistantDTO.builder()
                .assistantId(assistant.getAssistantId())
                .document(assistant.getDocument())
                .email(assistant.getEmail())
                .firstName(assistant.getFirstName())
                .lastName(assistant.getLastName())
                .build();
    }
}
