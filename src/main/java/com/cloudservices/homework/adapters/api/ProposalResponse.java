package com.cloudservices.homework.adapters.api;

import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProposalResponse {

    private final String id;
    private final String name;
    private final String content;
    private final ProposalState status;

    public static ProposalResponse of(Proposal proposal) {
        return new ProposalResponse(
                proposal.getId(),
                proposal.getName(),
                proposal.getContent(),
                proposal.getState()
        );
    }

}
