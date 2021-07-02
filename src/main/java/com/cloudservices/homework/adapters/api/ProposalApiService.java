package com.cloudservices.homework.adapters.api;

import com.cloudservices.homework.domain.model.proposal.Proposal;
import com.cloudservices.homework.domain.model.proposal.ProposalState;
import com.cloudservices.homework.domain.ports.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;

@Component
@RequiredArgsConstructor
class ProposalApiService {

    private final ProposalService service;

    ProposalResponse create(CreateProposalRequest request) {
        Proposal proposal = service.create(request.getName(), request.getContent());
        return ProposalResponse.of(proposal);
    }

    ProposalResponse updateContent(UpdateProposalContentRequest request) {
        return null;
    }

    ProposalResponse updateState(UpdateProposalStateRequest request) {
        return null;
    }

    void delete(String id) {

    }

    Page<ProposalResponse> findPage(String name, ProposalState state, Pageable pageable) {
        return null;
    }
}
