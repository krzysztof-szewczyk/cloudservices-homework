package com.cloudservices.homework.adapters.api;

import com.cloudservices.homework.domain.model.proposal.ProposalState;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.print.Pageable;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proposal")
class ProposalEndpoint {

    private final ProposalApiService proposalApiService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ProposalResponse create(@RequestBody @Valid CreateProposalRequest createProposalRequest) {
        return proposalApiService.create(createProposalRequest);
    }

    @PutMapping
    @ResponseStatus(OK)
    public ProposalResponse updateContent(@RequestBody @Valid UpdateProposalContentRequest updateProposalStateRequest) {
        return proposalApiService.updateContent(updateProposalStateRequest);
    }

    @PutMapping
    @ResponseStatus(OK)
    public ProposalResponse updateState(@RequestBody @Valid UpdateProposalStateRequest updateProposalStateRequest) {
        return proposalApiService.updateState(updateProposalStateRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String id) {
        proposalApiService.delete(id);
    }

    @GetMapping
    @ResponseStatus(OK)
    public Page<ProposalResponse> findPage(@RequestParam String name,
                                          @RequestParam ProposalState state,
                                          @PageableDefault Pageable pageable) {
        return proposalApiService.findPage(name, state, pageable);
    }

}
