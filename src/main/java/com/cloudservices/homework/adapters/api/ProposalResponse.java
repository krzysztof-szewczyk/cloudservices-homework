package com.cloudservices.homework.adapters.api;

import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
class ProposalResponse {

    String id;
    String name;
    String content;
    ProposalState state;

    public static ProposalResponse of(Proposal proposal) {
        return ProposalResponse.builder()
                .id(proposal.getId())
                .name(proposal.getName())
                .content(proposal.getContent())
                .state(proposal.getState())
                .build();
    }

}
