package com.cloudservices.homework.adapters.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
class CreateProposalRequest {
    String name;
    String content;

    @JsonCreator
    public CreateProposalRequest(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
