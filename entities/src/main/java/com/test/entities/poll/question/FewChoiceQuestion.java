package com.test.entities.poll.question;

import com.test.eunms.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "few_question_entity")
public class FewChoiceQuestion extends Question{

    @ElementCollection
    @Column(name = "questions_list")
    List<Question> questionsForChoice;

    @ElementCollection
    @Column(name = "right_answers")
    private List<Integer> rightAnswers;

    @ElementCollection
    @Column(name = "user_answers")
    private List<Integer> userAnswers;

    @Column(name = "question_type")
    private QuestionType questionType = QuestionType.ANSWER_WITH_FEW_CHOICE;
}
