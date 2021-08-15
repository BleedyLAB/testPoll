package com.test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.test.entities.poll.question.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
}
