package com.cloudservices.homework.domain.ports;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository repository;

    public Proposal create(String name, String content) {
        NewProposal newProposal = new NewProposal(name, content);
        return repository.save(newProposal);
    }

    public Proposal update(String id, String name, String content, ProposalState status) {
        Proposal proposal = new Proposal(id, name, content, status);
        return repository.update(proposal);
    }

    public void delete(String id) {

    }

    public Page<Proposal> getPage(String name, ProposalState status, Pageable pageable) {
        return null;
    }
}
