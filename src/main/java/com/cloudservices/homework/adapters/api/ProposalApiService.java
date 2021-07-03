package com.cloudservices.homework.adapters.api;

import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import com.cloudservices.homework.domain.ports.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
class ProposalApiService {

    private final ProposalService service;

    ProposalResponse create(CreateProposalRequest request) {
        Proposal proposal = service.create(request.getName(), request.getContent());
        return ProposalResponse.of(proposal);
    }

    ProposalResponse updateContent(UpdateProposalContentRequest request) {
        Proposal proposal = service.updateContent(request.getId(), request.getContent());
        return ProposalResponse.of(proposal);
    }

    ProposalResponse updateState(UpdateProposalStateRequest request) {
        Proposal proposal = service.updateState(request.getId(), request.getState(), request.getReason());
        return ProposalResponse.of(proposal);
    }

    Page<ProposalResponse> findByNameOrState(String name, ProposalState state, Pageable pageable) {
        Page<Proposal> proposalPage = service.findByNameOrState(name, state, pageable);
        return proposalPage.map(ProposalResponse::of);
    }
}
