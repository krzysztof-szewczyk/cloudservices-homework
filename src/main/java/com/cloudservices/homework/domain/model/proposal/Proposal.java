package com.cloudservices.homework.domain.model.proposal;

import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalContentCannotBeUpdatedException;
import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalStateCannotBeUpdatedException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.cloudservices.homework.domain.model.proposal.ProposalState.PUBLISHED;
import static com.cloudservices.homework.domain.model.proposal.ProposalState.getReasonRequiredStates;
import static com.cloudservices.homework.domain.model.proposal.ProposalState.getUpdatableContentStates;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Proposal {
    private final String id;
    private final String name;
    private String content;
    private ProposalState state;
    private String reason;
    private Long uuid;

    public void setContent(String content) {
        validateContentUpdate(content);
        this.content = content;
    }

    public void setState(ProposalState state) {
        validateStateUpdate(state);
        this.state = state;
    }

    public void setUuid(Long uuid) {
        if (state == PUBLISHED) {
            this.uuid = uuid;
        }
    }

    public void setReason(String reason) {
        if (doesStateRequireReason(this.state)) {
            this.reason = reason;
        }
    }

    public static void validateStateRequirements(ProposalState state, String reason) {
        if (doesStateRequireReason(state) && isBlank(reason)) {
            throw new ProposalStateCannotBeUpdatedException();
        }
    }

    private void validateContentUpdate(String content) {
        if (isNull(content) || !hasUpdatableContent()) {
            throw new ProposalContentCannotBeUpdatedException();
        }
    }

    private boolean hasUpdatableContent() {
        return getUpdatableContentStates().contains(state);
    }

    private void validateStateUpdate(ProposalState state) {
        if (isNull(state) || !hasProperNextState(state)) {
            throw new ProposalStateCannotBeUpdatedException();
        }
    }

    private boolean hasProperNextState(ProposalState state) {
        return this.state.getAllowedNextStates()
                .contains(state);
    }

    private static boolean doesStateRequireReason(ProposalState state) {
        return getReasonRequiredStates().contains(state);
    }
}
