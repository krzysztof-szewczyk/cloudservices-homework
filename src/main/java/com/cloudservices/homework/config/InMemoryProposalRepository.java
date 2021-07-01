package com.cloudservices.homework.config;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.ports.ProposalRepository;

public class InMemoryProposalRepository implements ProposalRepository {
    @Override
    public Proposal save(NewProposal newProposal) {
        return null;
    }

    @Override
    public Proposal update(Proposal proposal) {
        return null;
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
