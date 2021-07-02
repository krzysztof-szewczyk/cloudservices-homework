package com.cloudservices.homework.domain.model.proposal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private final Set<ProposalState> allowedNextStates;
    private final boolean contentUpdatable;

    public Set<ProposalState> getUpdatableContentStates() {
        return Stream.of(ProposalState.values())
                .filter(ProposalState::isContentUpdatable)
                .collect(Collectors.toSet());
    }
}
