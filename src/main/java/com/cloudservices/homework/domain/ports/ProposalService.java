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

    public Proposal updateContent(String id, String content) {
        Proposal proposal = repository.findById(id);
        proposal.setContent(content);
        return repository.update(proposal);
    }

    public Proposal updateState(String id, ProposalState state) {
        Proposal proposal = repository.findById(id);
        proposal.setState(state);
        return repository.update(proposal);
    }

    public void delete(String id) {
    }

    public Page<Proposal> findPage(String name, ProposalState state, Pageable pageable) {
        return null;
    }
}
