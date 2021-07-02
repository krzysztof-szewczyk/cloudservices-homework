package com.cloudservices.homework.config;

import com.cloudservices.homework.adapters.db.InMemoryProposalRepository;
import com.cloudservices.homework.domain.ports.ProposalRepository;
import com.cloudservices.homework.domain.ports.ProposalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HomeworkConfig {

    ProposalService getProposalService() {
        return new ProposalService(new InMemoryProposalRepository());
    }

    @Bean
    ProposalService getProposalService(ProposalRepository proposalRepository) {
        return new ProposalService(proposalRepository);
    }

}
