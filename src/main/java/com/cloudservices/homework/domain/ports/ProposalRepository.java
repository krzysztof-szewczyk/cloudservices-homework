package com.cloudservices.homework.domain.ports;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;

public interface ProposalRepository {

    Proposal save(NewProposal newProposal);

    Proposal update(Proposal proposal);

    void delete(String id);

    Proposal findById(String id);

    Proposal existsByIdAndState(String id, ProposalState state);

    void findAll();
}
