package com.cloudservices.homework.adapters.db;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import com.cloudservices.homework.domain.ports.ProposalRepository;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.cloudservices.homework.domain.model.proposal.ProposalState.CREATED;

public class InMemoryProposalRepository implements ProposalRepository {

    private final ConcurrentMap<String, Proposal> map = new ConcurrentHashMap<>();

    @Override
    public Proposal save(NewProposal newProposal) {
        String id = ObjectId.get().toString();
        Proposal proposal = new Proposal(id, newProposal.getName(), newProposal.getContent(), CREATED, null);
        map.put(id, proposal);
        return proposal;
    }

    @Override
    public Proposal update(Proposal proposal) {
//        map.get(proposal.getId());
        return null;
    }

    @Override
    public Proposal findById(String id) {
        return null;
    }

    @Override
    public Page<Proposal> findByNameAndState(String name, ProposalState state, Pageable pageable) {
        return null;
    }
}
