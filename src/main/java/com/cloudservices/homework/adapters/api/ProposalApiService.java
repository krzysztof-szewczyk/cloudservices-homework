package com.cloudservices.homework.adapters.api;

import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.ports.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ProposalApiService {

    private final ProposalService service;

    public ProposalResponse create(CreateProposalRequest request) {
        Proposal proposal = service.create(request.getName(), request.getContent());
        return ProposalResponse.of(proposal);
    }
}
