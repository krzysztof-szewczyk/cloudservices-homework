package com.cloudservices.homework.adapters.api;

import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Value
@Builder
@JsonInclude(NON_NULL)
class ProposalResponse {

    String id;
    String name;
    String content;
    ProposalState state;
    String reason;
    long uuid;

    public static ProposalResponse of(Proposal proposal) {
        return ProposalResponse.builder()
                .id(proposal.getId())
                .name(proposal.getName())
                .content(proposal.getContent())
                .state(proposal.getState())
                .reason(proposal.getReason())
                .uuid(proposal.getUuid())
                .build();
    }

}
