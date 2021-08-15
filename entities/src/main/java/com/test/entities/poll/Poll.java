package com.test.entities.poll;

import com.test.entities.BaseEntity;
import com.test.entities.poll.question.FewChoiceQuestion;
import com.test.entities.poll.question.OneChoiceQuestion;
import com.test.entities.poll.question.TextQuestion;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "poll_entity")
public class Poll extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @ElementCollection
    @Column(name = "text_questions_list")
    private List<TextQuestion> textQuestionsList;
    @ElementCollection
    @Column(name = "one_choice_questions_list")
    private List<OneChoiceQuestion>oneChoiceQuestionsList;
    @ElementCollection
    @Column(name = "few_choice_questions_list")
    private List<FewChoiceQuestion> FewChoiceQuestionsList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Poll poll = (Poll) o;
        return Objects.equals(description, poll.description) && Objects.equals(startTime, poll.startTime) && Objects.equals(finishTime, poll.finishTime) && Objects.equals(textQuestionsList, poll.textQuestionsList) && Objects.equals(oneChoiceQuestionsList, poll.oneChoiceQuestionsList) && Objects.equals(FewChoiceQuestionsList, poll.FewChoiceQuestionsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, startTime, finishTime, textQuestionsList, oneChoiceQuestionsList, FewChoiceQuestionsList);
    }
}
