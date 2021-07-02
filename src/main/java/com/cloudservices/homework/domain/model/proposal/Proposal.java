package com.cloudservices.homework.domain.model.proposal;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class Proposal {
    String id;
    String name;
    String content;
    ProposalState state;
}
