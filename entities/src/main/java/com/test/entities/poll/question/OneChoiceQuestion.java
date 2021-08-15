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
@Table(name = "one_question_entity")
public class OneChoiceQuestion extends Question{

    @ElementCollection
    @Column(name = "questions_list")
    List<Question> questionsForChoice;

    @Column(name = "right_answer")
    private Integer rightAnswer;

    @Column(name = "user_answer")
    private Integer userAnswer;

    @Column(name = "question_type")
    private QuestionType questionType = QuestionType.ANSWER_WITH_ONE_CHOICE;
}
