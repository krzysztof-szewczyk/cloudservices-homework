package com.cloudservices.homework.domain.model.proposal;

import lombok.Getter;

@Getter
public class NewProposal {
    String name;
    String content;

    public NewProposal(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
