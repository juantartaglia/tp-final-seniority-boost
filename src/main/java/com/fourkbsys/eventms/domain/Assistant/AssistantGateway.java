package com.fourkbsys.eventms.domain.Assistant;

import java.util.Optional;

public interface AssistantGateway {

    Optional<Assistant> findById(Long id);
    Assistant saveAssistant(Assistant assistant);

}
