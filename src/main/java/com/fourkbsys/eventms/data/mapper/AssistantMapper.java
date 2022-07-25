package com.fourkbsys.eventms.data.mapper;

import com.fourkbsys.eventms.data.AssistantEntity;
import com.fourkbsys.eventms.domain.Assistant.Assistant;

public abstract class AssistantMapper {
    public static Assistant toAssistant(AssistantEntity assistantEntity){
        Assistant assistant = new Assistant();
        assistant.setAssistantId(assistantEntity.getAssistantId());
        assistant.setDocument(assistantEntity.getDocument());
        assistant.setEmail(assistantEntity.getEmail());
        assistant.setFirstName(assistantEntity.getFirstName());
        assistant.setLastName(assistantEntity.getLastName());
        return assistant;
    }

    public static AssistantEntity toAssistantEntity(Assistant assistant) {
        return AssistantEntity.builder()
                .assistantId(assistant.getAssistantId())
                .document(assistant.getDocument())
                .email(assistant.getEmail())
                .firstName(assistant.getFirstName())
                .lastName(assistant.getLastName())
                .build();
    }

}
