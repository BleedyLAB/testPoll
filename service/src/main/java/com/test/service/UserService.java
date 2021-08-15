package com.test.service;

import com.test.core.exception.PollIdNotFoundException;
import com.test.core.exception.UserIdNotFoundException;
import com.test.entities.poll.Poll;
import com.test.entities.users.User;

import java.util.List;

public interface UserService {
    List<Poll> getListOfActivePoll();

    Poll getPollForStart(Long userId, Long pollId) throws UserIdNotFoundException, PollIdNotFoundException;

    List<Poll> getFinishedPollList(Long userId) throws UserIdNotFoundException;

    User saveFinishedPoll(Long userId,Poll poll) throws UserIdNotFoundException;
}
