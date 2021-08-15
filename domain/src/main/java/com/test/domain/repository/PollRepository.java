package com.test.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.test.entities.poll.Poll;

import java.util.Optional;

@Repository
public interface PollRepository extends JpaRepository<Poll,Long> {

}
