package com.cloudservices.homework.adapters.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
class CreateProposalRequest {

    @NotNull
    private final String name;

    @NotNull
    private final String content;

    @JsonCreator
    public CreateProposalRequest(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
