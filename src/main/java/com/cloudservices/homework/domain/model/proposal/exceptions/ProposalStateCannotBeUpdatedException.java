package com.cloudservices.homework.domain.model.proposal.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value = BAD_REQUEST, reason = "Proposal state cannot be updated")
public class ProposalStateCannotBeUpdatedException extends ProposalException {

}
