package com.cloudservices.homework.domain.model.proposal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.cloudservices.homework.domain.model.proposal.ProposalState.getUpdatableContentStates;
import static java.util.Objects.isNull;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Proposal {
    private final String id;
    private final String name;
    private String content;
    private ProposalState state;

    public void setContent(String content) {
        validateContentUpdate(content);
        this.content = content;
    }

    public void setState(ProposalState state) {
        validateStateUpdate(state);
        this.state = state;
    }

    private void validateContentUpdate(String content) {
        if (isNull(content) || !hasUpdatableContent()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasUpdatableContent() {
        return getUpdatableContentStates().contains(state);
    }

    private void validateStateUpdate(ProposalState state) {
        if (isNull(state) || !hasProperNextState(state)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean hasProperNextState(ProposalState state) {
        return state.getAllowedNextStates().contains(state);
    }

}
