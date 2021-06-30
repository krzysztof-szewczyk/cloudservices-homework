package com.cloudservices.homework.domain.model.proposal;

import lombok.Value;

@Value
public class Proposal {
    String id;
    String name;
    String content;
    ProposalState state;
}
