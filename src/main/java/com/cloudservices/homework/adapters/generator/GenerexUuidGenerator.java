package com.cloudservices.homework.adapters.generator;

import com.cloudservices.homework.adapters.generator.exceptions.CannotGenerateProposalUuidException;
import com.cloudservices.homework.domain.ports.ProposalRepository;
import com.cloudservices.homework.domain.ports.UuidGenerator;
import com.mifmif.common.regex.Generex;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.lang.Long.parseLong;

@Component
@RequiredArgsConstructor
class GenerexUuidGenerator implements UuidGenerator {

    private static final Generex generator = new Generex("[1-9][0-9]{17}");

    private final ProposalRepository repository;

    @Override
    public Long generateUuid() {
        Long uuid;
        int index = 0;
        int maxRetry = 10;
        boolean alreadyExists;
        do {
            uuid = randomUuid();
            alreadyExists = repository.existsByUuid(uuid);
            index++;
        } while (index < maxRetry && alreadyExists);

        if (alreadyExists) {
            throw new CannotGenerateProposalUuidException();
        }
        return uuid;
    }

    private static Long randomUuid() {
        return parseLong(generator.random());
    }
}
