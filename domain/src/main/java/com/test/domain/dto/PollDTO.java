package com.test.domain.dto;

import com.test.entities.poll.question.FewChoiceQuestion;
import com.test.entities.poll.question.OneChoiceQuestion;
import com.test.entities.poll.question.TextQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollDTO {

    private String description;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private List<TextQuestion> textQuestionsList;
    private List<OneChoiceQuestion> oneChoiceQuestionsList;
    private List<FewChoiceQuestion> FewChoiceQuestionsList;
}
