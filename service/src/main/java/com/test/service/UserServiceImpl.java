package com.test.service;

import com.test.core.exception.PollIdNotFoundException;
import com.test.core.exception.UserIdNotFoundException;
import com.test.entities.poll.Poll;
import com.test.entities.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.domain.repository.PollRepository;
import com.test.domain.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    PollRepository pollRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Poll> getListOfActivePoll() {
        List<Poll> polls= pollRepository.findAll();
        return polls.stream()
                .filter(a -> a.getArchive().equals(false))
                .collect(Collectors.toList());
    }

    @Override
    public Poll getPollForStart(Long userId, Long pollId) throws PollIdNotFoundException {
        User user = userRepository.findById(userId).orElse(new User(userId));
        userRepository.save(user);
        return pollRepository.findById(pollId)
                .orElseThrow(()-> new PollIdNotFoundException("Poll not found"));

    }

    @Override
    public List<Poll> getFinishedPollList(Long userId) throws UserIdNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserIdNotFoundException("User not found"));
        return user.getFinishedPoll();
    }

    @Override
    public User saveFinishedPoll(Long userId, Poll poll) throws UserIdNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserIdNotFoundException("User not found"));
        user.getFinishedPoll().add(poll);
        userRepository.save(user);
        return user;
    }
}
