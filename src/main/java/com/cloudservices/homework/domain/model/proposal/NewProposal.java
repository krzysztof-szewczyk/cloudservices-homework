package com.cloudservices.homework.domain.model.proposal;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@EqualsAndHashCode
public class NewProposal {
    String name;
    String content;

    public NewProposal(String name, String content) {
        this.name = name;
        this.content = content;
        validateIfNotNullFields();
    }

    void validateIfNotNullFields() {
        if (checkIfAnyFieldIsNull()) {
            throw new IllegalArgumentException();
        }
    }

    private boolean checkIfAnyFieldIsNull() {
        return Stream.of(name, content)
                .anyMatch(Objects::isNull);
    }
}
