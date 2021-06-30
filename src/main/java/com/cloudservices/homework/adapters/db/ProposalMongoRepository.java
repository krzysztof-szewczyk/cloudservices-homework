package com.cloudservices.homework.adapters.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalMongoRepository extends MongoRepository<ProposalDbModel, String> {

}
