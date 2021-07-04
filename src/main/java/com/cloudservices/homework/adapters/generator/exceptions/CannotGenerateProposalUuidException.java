package com.cloudservices.homework.adapters.generator.exceptions;

import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = INTERNAL_SERVER_ERROR, reason = "Cannot generate proposal UUID")
public class CannotGenerateProposalUuidException extends ProposalException {

}
