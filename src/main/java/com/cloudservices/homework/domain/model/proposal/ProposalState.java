package com.cloudservices.homework.domain.model.proposal;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

@AllArgsConstructor
@Getter
public enum ProposalState {
    PUBLISHED(emptySet(), false, false),
    REJECTED(emptySet(), false, true),
    ACCEPTED(setAllowedNextStates(PUBLISHED, REJECTED), false, false),
    VERIFIED(setAllowedNextStates(REJECTED, ACCEPTED), true, false),
    DELETED(emptySet(), false, true),
    CREATED(setAllowedNextStates(DELETED, VERIFIED), true, false);

    private final Set<ProposalState> allowedNextStates;
    private final boolean contentUpdatable;
    private final boolean reasonRequired;

    static Set<ProposalState> getUpdatableContentStates() {
        return Stream.of(ProposalState.values())
                .filter(ProposalState::isContentUpdatable)
                .collect(toSet());
    }

    public static Set<ProposalState> getReasonRequiredStates() {
        return Stream.of(ProposalState.values())
                .filter(ProposalState::isReasonRequired)
                .collect(toSet());
    }

    private static Set<ProposalState> setAllowedNextStates(ProposalState... states) {
        return Stream.of(states)
                .collect(toSet());
    }
}
