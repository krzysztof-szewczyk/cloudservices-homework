package com.cloudservices.homework.adapters.db;

import com.cloudservices.homework.domain.model.proposal.ProposalState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

interface SpringDataMongoProposalRepository extends MongoRepository<ProposalDbModel, String> {

    Page<ProposalDbModel> findByNameOrState(String name, ProposalState state, Pageable pageable);
}
