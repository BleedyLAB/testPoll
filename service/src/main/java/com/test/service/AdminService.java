package com.test.service;

import com.test.core.exception.PollIdNotFoundException;
import com.test.entities.poll.Poll;


public interface AdminService {
    Poll createNewPoll(Poll poll);

    void deletePoll(Long id);

    Poll updatePoll(Long id, Poll newPoll) throws PollIdNotFoundException;

}
