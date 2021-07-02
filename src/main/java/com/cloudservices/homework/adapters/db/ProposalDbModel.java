package com.cloudservices.homework.adapters.db;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.cloudservices.homework.domain.model.proposal.ProposalState.CREATED;

@Document(collection = "proposals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class ProposalDbModel {

    @Id
    String id;
    String name;
    String content;
    ProposalState state;

    public static ProposalDbModel of(NewProposal newProposal) {
        return new ProposalDbModel(
                null,
                newProposal.getName(),
                newProposal.getContent(),
                CREATED
        );
    }

    public static ProposalDbModel of(Proposal proposal) {
        return new ProposalDbModel(
                proposal.getId(),
                proposal.getName(),
                proposal.getContent(),
                proposal.getState()
        );
    }

    public static Proposal from(ProposalDbModel proposalDbModel) {
        return new Proposal(
                proposalDbModel.getId(),
                proposalDbModel.getName(),
                proposalDbModel.getContent(),
                proposalDbModel.getState()
        );
    }
}
