package com.cloudservices.homework.adapters.db;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

@JaversSpringDataAuditable
interface SpringDataMongoProposalRepository extends MongoRepository<ProposalDbModel, String> {
    boolean existsByUuid(Long uuid);
}
