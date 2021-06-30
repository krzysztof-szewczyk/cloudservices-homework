package com.cloudservices.homework.domain.ports;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository repository;

    public Proposal create(String name, String content) {
        NewProposal newProposal = new NewProposal(name, content);
        return repository.save(newProposal);
    }
}
