package com.cloudservices.homework.config;

import com.cloudservices.homework.domain.ports.ProposalRepository;
import com.cloudservices.homework.domain.ports.ProposalService;
import com.cloudservices.homework.domain.ports.UuidGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HomeworkConfig {

    @Bean
    ProposalService getProposalService(ProposalRepository proposalRepository,
                                       UuidGenerator generator) {
        return new ProposalService(proposalRepository, generator);
    }

}
