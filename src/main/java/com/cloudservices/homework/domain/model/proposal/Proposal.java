package com.cloudservices.homework.domain.model.proposal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Proposal {
    private final String id;
    private final String name;
    @Setter
    private String content;
    @Setter
    private ProposalState state;

}
