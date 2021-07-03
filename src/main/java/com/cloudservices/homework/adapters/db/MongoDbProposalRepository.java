package com.cloudservices.homework.adapters.db;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
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
        return save(proposalDbModel);
    }

    @Override
    public Proposal update(Proposal proposal) {
        ProposalDbModel proposalDbModel = ProposalDbModel.of(proposal);
        return save(proposalDbModel);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Proposal findById(String id) {
        return null;
    }

    @Override
    public Proposal existsByIdAndState(String id, ProposalState state) {
        return null;
    }

    @Override
    public void findAll() {

    }

    private Proposal save(ProposalDbModel proposalDbModel) {
        ProposalDbModel saved = repository.save(proposalDbModel);
        return ProposalDbModel.from(saved);
    }
}
