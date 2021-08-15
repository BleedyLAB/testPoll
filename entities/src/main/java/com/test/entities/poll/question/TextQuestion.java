package com.test.entities.poll.question;

import com.test.eunms.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "text_question_entity")
public class TextQuestion extends Question{

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "question_type")
    private QuestionType questionType = QuestionType.TEXT_ANSWER;
}
