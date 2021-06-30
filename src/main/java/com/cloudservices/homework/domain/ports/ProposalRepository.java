package com.cloudservices.homework.domain.ports;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;

public interface ProposalRepository {
    Proposal save(NewProposal newProposal);

    void update(Proposal proposal);

    void delete(Long id);

    void findById(Long id);

    void findAll();
}
