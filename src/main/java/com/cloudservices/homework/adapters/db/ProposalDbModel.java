package com.cloudservices.homework.adapters.db;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.cloudservices.homework.domain.model.proposal.ProposalState.CREATED;

@Document(collection = "proposals")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
class ProposalDbModel {

    @Id
    String id;
    String name;
    String content;
    ProposalState state;
    String reason;
    long uuid;

    public static ProposalDbModel of(NewProposal newProposal) {
        return ProposalDbModel.builder()
                .id(null)
                .name(newProposal.getName())
                .content(newProposal.getContent())
                .state(CREATED)
                .reason(null)
                .build();
    }

    public static ProposalDbModel of(Proposal proposal) {
        return ProposalDbModel.builder()
                .id(proposal.getId())
                .name(proposal.getName())
                .content(proposal.getContent())
                .state(proposal.getState())
                .reason(proposal.getReason())
                .uuid(proposal.getUuid())
                .build();
    }

    public static Proposal from(ProposalDbModel proposalDbModel) {
        return Proposal.builder()
                .id(proposalDbModel.getId())
                .name(proposalDbModel.getName())
                .content(proposalDbModel.getContent())
                .state(proposalDbModel.getState())
                .reason(proposalDbModel.getReason())
                .uuid(proposalDbModel.getUuid())
                .build();
    }
}
