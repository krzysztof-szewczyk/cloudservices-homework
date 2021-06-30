package com.cloudservices.homework.config;

import com.cloudservices.homework.adapters.db.ProposalMongoRepository;
import com.cloudservices.homework.adapters.db.ProposalRepositoryImpl;
import com.cloudservices.homework.domain.ports.ProposalRepository;
import com.cloudservices.homework.domain.ports.ProposalService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HomeworkConfig {

    @Bean
    public ProposalService getProposalMongoRepository(ProposalMongoRepository proposalMongoRepository) {
        ProposalRepository proposalRepository = new ProposalRepositoryImpl(proposalMongoRepository);
        return new ProposalService(proposalRepository);
    }

}
