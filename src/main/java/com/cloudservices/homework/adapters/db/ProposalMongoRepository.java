package com.cloudservices.homework.adapters.db;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProposalMongoRepository extends MongoRepository<ProposalDbModel, String> {

}
