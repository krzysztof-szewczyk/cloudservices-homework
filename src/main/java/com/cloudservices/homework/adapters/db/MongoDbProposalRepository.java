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
    public void update(Proposal proposal) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void findById(Long id) {

    }

    @Override
    public void findAll() {

    }
}
