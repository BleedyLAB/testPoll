package com.test.entities.poll.question;

import com.test.entities.BaseEntity;
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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "question_entity")
public class Question extends BaseEntity {

    @Column(name = "header")
    private String header;

    @Column(name = "question_text")
    private String questionText;

}
