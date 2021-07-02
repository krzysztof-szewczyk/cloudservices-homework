package com.cloudservices.homework.adapters.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
class UpdateProposalContentRequest {

    @NotNull
    private final String id;

    @NotNull
    private final String content;

    @JsonCreator
    public UpdateProposalContentRequest(String id, String content) {
        this.id = id;
        this.content = content;
    }
}
