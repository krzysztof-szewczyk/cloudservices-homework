package com.cloudservices.homework.config;

import com.cloudservices.homework.domain.ports.ProposalRepository;
import com.cloudservices.homework.domain.ports.ProposalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HomeworkConfig {

    @Bean
    public ProposalService getProposalMongoRepository(ProposalRepository proposalRepository) {
        return new ProposalService(proposalRepository);
    }

}
