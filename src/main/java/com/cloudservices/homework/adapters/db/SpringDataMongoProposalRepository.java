package com.cloudservices.homework.adapters.db;

import org.springframework.data.mongodb.repository.MongoRepository;

interface SpringDataMongoProposalRepository extends MongoRepository<ProposalDbModel, String> {

}
