package com.cloudservices.homework.adapters.db;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import com.cloudservices.homework.adapters.db.exceptions.ProposalNotFoundException;
import com.cloudservices.homework.domain.ports.ProposalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Proposal findById(String id) {
        return repository.findById(id)
                .map(ProposalDbModel::from)
                .orElseThrow(ProposalNotFoundException::new);
    }

    @Override
    public Page<Proposal> findByNameOrState(String name, ProposalState state, Pageable pageable) {
        Page<ProposalDbModel> page = repository.findByNameOrState(name, state, pageable);
        return page.map(ProposalDbModel::from);
    }

    private Proposal save(ProposalDbModel proposalDbModel) {
        ProposalDbModel saved = repository.save(proposalDbModel);
        return ProposalDbModel.from(saved);
    }
}
