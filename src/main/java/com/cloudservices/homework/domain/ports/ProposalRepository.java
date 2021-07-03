package com.cloudservices.homework.domain.ports;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProposalRepository {

    Proposal save(NewProposal newProposal);

    Proposal update(Proposal proposal);

    Proposal findById(String id);

    Page<Proposal> findByNameAndState(String name, ProposalState state, Pageable pageable);

    boolean existsByUuid(Long uuid);
}
