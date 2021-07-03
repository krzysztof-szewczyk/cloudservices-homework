package com.cloudservices.homework.domain.ports;

import com.cloudservices.homework.domain.model.proposal.NewProposal;
import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import com.mifmif.common.regex.Generex;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.cloudservices.homework.domain.model.proposal.Proposal.validateStateRequirements;
import static com.cloudservices.homework.domain.model.proposal.ProposalState.PUBLISHED;
import static java.lang.Long.parseLong;

@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository repository;

    public Proposal create(String name, String content) {
        NewProposal newProposal = new NewProposal(name, content);
        return repository.save(newProposal);
    }

    public Proposal updateContent(String id, String content) {
        Proposal proposal = repository.findById(id);
        proposal.setContent(content);
        return repository.update(proposal);
    }

    public Proposal updateState(String id, ProposalState state, String reason) {
        validateStateRequirements(state, reason);
        Proposal proposal = repository.findById(id);
        proposal.setState(state);
        proposal.setReason(reason);
        applyUuidIfPublished(proposal);
        return repository.update(proposal);
    }

    public Page<Proposal> findByNameOrState(String name, ProposalState state, Pageable pageable) {
        return repository.findByNameAndState(name, state, pageable);
    }


    private void applyUuidIfPublished(Proposal proposal) {
        if (proposal.getState() == PUBLISHED) {
            proposal.setUuid(generateUuid());
        }
    }

    private Long generateUuid() {
        Long uuid;
        int index = 0;
        int maxRetry = 10;
        boolean alreadyExists;
        do {
            uuid = randomUuid();
            alreadyExists = repository.existsByUuid(uuid);
            index++;
        } while (index < maxRetry && alreadyExists);

        if (alreadyExists) {
            throw new IllegalArgumentException();
        }
        return uuid;
    }

    private long randomUuid() {
        return parseLong(new Generex("[1-9][0-9]{17}").random());
    }
}
