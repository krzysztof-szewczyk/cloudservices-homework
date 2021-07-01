package com.cloudservices.homework.adapters.api;

import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import lombok.Value;

@Value
class ProposalResponse {

    String id;
    String name;
    String content;
    ProposalState status;

    public static ProposalResponse of(Proposal proposal) {
        return new ProposalResponse(
                proposal.getId(),
                proposal.getName(),
                proposal.getContent(),
                proposal.getState()
        );
    }

}
