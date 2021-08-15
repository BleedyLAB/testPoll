package com.test.service;

import com.test.core.exception.PollIdNotFoundException;
import com.test.core.exception.UserIdNotFoundException;
import com.test.entities.poll.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.domain.repository.PollRepository;


@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    PollRepository pollRepository;

    @Override
    public Poll createNewPoll(Poll poll) {
        Poll newPoll = new Poll();
        newPoll.setDescription(poll.getDescription());
        newPoll.setStartTime(poll.getStartTime());
        newPoll.setFinishTime(poll.getFinishTime());
        sortQuestionAndInsert(newPoll,poll);
        pollRepository.save(newPoll);
        return newPoll;
    }

    @Override
    public void deletePoll(Long id) {
        pollRepository.deleteById(id);
    }

    @Override
    public Poll updatePoll(Long id, Poll newPoll) throws PollIdNotFoundException {
        Poll oldPoll = pollRepository.findById(id).orElseThrow(()-> new PollIdNotFoundException("Poll not found"));
        oldPoll.setTextQuestionsList(newPoll.getTextQuestionsList());
        oldPoll.setOneChoiceQuestionsList(newPoll.getOneChoiceQuestionsList());
        oldPoll.setFewChoiceQuestionsList(newPoll.getFewChoiceQuestionsList());
        oldPoll.setDescription(newPoll.getDescription());
        oldPoll.setFinishTime(newPoll.getFinishTime());
        oldPoll.setStartTime(newPoll.getStartTime());
        pollRepository.save(oldPoll);
        return oldPoll;
    }


    private void sortQuestionAndInsert(Poll pollToInsert, Poll questionPoll){
        pollToInsert.setTextQuestionsList(questionPoll.getTextQuestionsList());
        pollToInsert.setOneChoiceQuestionsList(questionPoll.getOneChoiceQuestionsList());
        pollToInsert.setFewChoiceQuestionsList(questionPoll.getFewChoiceQuestionsList());
    }
}
