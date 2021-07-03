package com.cloudservices.homework.adapters.api;

import com.cloudservices.homework.domain.model.proposal.ProposalState;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
class UpdateProposalStateRequest {

    @NotNull
    private final String id;

    @NotNull
    private final ProposalState state;

    private final String reason;

    @JsonCreator
    public UpdateProposalStateRequest(String id, ProposalState state, String reason) {
        this.id = id;
        this.state = state;
        this.reason = reason;
    }
}
