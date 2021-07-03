package com.cloudservices.homework.adapters.db.exceptions;

import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalException;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Proposal not found")
public class ProposalNotFoundException extends ProposalException {

}
