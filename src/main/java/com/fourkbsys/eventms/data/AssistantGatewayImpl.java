package com.fourkbsys.eventms.data;

import com.fourkbsys.eventms.data.mapper.AssistantMapper;
import com.fourkbsys.eventms.domain.Assistant.Assistant;
import com.fourkbsys.eventms.domain.Assistant.AssistantGateway;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public class AssistantGatewayImpl implements AssistantGateway {

    private final AssistantEntityPostgresqlRepository assistantRepository;

    public AssistantGatewayImpl(AssistantEntityPostgresqlRepository assistantRepository) {
        this.assistantRepository = assistantRepository;
    }

    @Override
    public Optional<Assistant> findById(Long id) {
        return assistantRepository.findById(id).map(AssistantMapper::toAssistant);
    }

    @Override
    public Assistant saveAssistant(Assistant assistant) {
        return Stream.of(assistant)
                .map(AssistantMapper::toAssistantEntity)
                .map(assistantRepository::save)
                .map(AssistantMapper::toAssistant)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Error mapping"));
    }
}
