package com.cloudservices.homework.domain.ports;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;

public interface ProposalRepository {

    Proposal save(NewProposal newProposal);

    Proposal update(Proposal proposal);

    void delete(String id);

    Proposal findById(String id);

    void findAll();
}
