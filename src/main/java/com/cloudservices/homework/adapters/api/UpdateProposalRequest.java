package com.cloudservices.homework.adapters.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
class UpdateProposalRequest {

    @NotNull
    private final String id;

    @NotNull
    private final String name;

    @NotNull
    private final String content;

    @JsonCreator
    public UpdateProposalRequest(String id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }
}
