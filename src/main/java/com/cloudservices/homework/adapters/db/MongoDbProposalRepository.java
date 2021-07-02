package com.cloudservices.homework.adapters.db;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.ports.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class MongoDbProposalRepository implements ProposalRepository {

    private final SpringDataMongoProposalRepository repository;

    @Override
    public Proposal save(NewProposal newProposal) {
        ProposalDbModel proposalDbModel = ProposalDbModel.of(newProposal);
        ProposalDbModel saved = repository.save(proposalDbModel);
        return ProposalDbModel.from(saved);
    }

    @Override
    public Proposal update(Proposal proposal) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Proposal findById(String id) {
        return null;
    }

    @Override
    public void findAll() {

    }
}
