package com.cloudservices.homework.domain.model.proposal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;

@AllArgsConstructor
@Getter
public enum ProposalState {
    PUBLISHED(emptySet(), false),
    REJECTED(emptySet(), false),
    ACCEPTED(newHashSet(PUBLISHED, REJECTED), false),
    VERIFIED(newHashSet(REJECTED, ACCEPTED), true),
    DELETED(emptySet(), false),
    CREATED(newHashSet(DELETED, VERIFIED), true);

    private final Set<ProposalState> allowedNextState;
    private final boolean contentUpdatable;

}
