package com.test.eunms;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionType {

    TEXT_ANSWER("Text answer"),
    ANSWER_WITH_ONE_CHOICE("Answer with one choice"),
    ANSWER_WITH_FEW_CHOICE("Answer with few choice");

    private final String type;
}
