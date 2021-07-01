package com.cloudservices.homework.adapters.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proposal")
class ProposalEndpoint {

    private final ProposalApiService proposalApiService;

    @PostMapping
    public ProposalResponse create(@RequestBody CreateProposalRequest createProposalRequest) {
        ProposalResponse proposalResponse = proposalApiService.create(createProposalRequest);
        return proposalResponse;
    }

}
