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
    PUBLISHED(emptySet(), false, false),
    REJECTED(emptySet(), false, true),
    ACCEPTED(newHashSet(PUBLISHED, REJECTED), false, false),
    VERIFIED(newHashSet(REJECTED, ACCEPTED), true, false),
    DELETED(emptySet(), false, true),
    CREATED(newHashSet(DELETED, VERIFIED), true, false);

    private final Set<ProposalState> allowedNextStates;
    private final boolean contentUpdatable;
    private final boolean reasonRequired;

    static Set<ProposalState> getUpdatableContentStates() {
        return Stream.of(ProposalState.values())
                .filter(ProposalState::isContentUpdatable)
                .collect(Collectors.toSet());
    }

    public static Set<ProposalState> getReasonRequiredStates() {
        return Stream.of(ProposalState.values())
                .filter(ProposalState::isReasonRequired)
                .collect(Collectors.toSet());
    }
}
