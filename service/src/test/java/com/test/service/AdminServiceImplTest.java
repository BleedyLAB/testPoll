package com.test.service;

import com.test.core.exception.PollIdNotFoundException;
import com.test.domain.repository.PollRepository;
import com.test.entities.poll.Poll;
import com.test.entities.poll.question.FewChoiceQuestion;
import com.test.entities.poll.question.OneChoiceQuestion;
import com.test.entities.poll.question.TextQuestion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
    @Mock
    PollRepository pollRepository;
    @Spy
    @InjectMocks
    AdminServiceImpl adminService;
    private Poll testPoll;

    @BeforeEach
    void setup() {
        Poll testPoll = new Poll();
        testPoll.setDescription("test");
        testPoll.setStartTime(LocalDateTime.of(2019,8,24,16,1));
        testPoll.setFinishTime(LocalDateTime.of(2019,8,24,23,24));
        testPoll.setTextQuestionsList(List.of(new TextQuestion()));
        testPoll.setOneChoiceQuestionsList(List.of(new OneChoiceQuestion()));
        testPoll.setFewChoiceQuestionsList(List.of(new FewChoiceQuestion()));
        this.testPoll = testPoll;
    }
    @Test
    void createNewPoll() {
        Poll newPoll = adminService.createNewPoll(testPoll);
        assertEquals(testPoll,newPoll);
    }

    @Test
    void deletePoll() {
        adminService.deletePoll(1L);
        InOrder inOrder = Mockito.inOrder(adminService,pollRepository);
        inOrder.verify(pollRepository).deleteById(any());
    }

    @Test
    void changePoll() throws PollIdNotFoundException {
        Mockito.when(pollRepository.findById(any())).thenReturn(Optional.of(new Poll()));
        Poll secondTestPoll = new Poll();
        secondTestPoll.setDescription("test1");
        secondTestPoll.setStartTime(LocalDateTime.of(2019,8,24,16,1));
        secondTestPoll.setFinishTime(LocalDateTime.of(2012,8,24,23,24));
        secondTestPoll.setTextQuestionsList(List.of(new TextQuestion(),new TextQuestion()));
        secondTestPoll.setOneChoiceQuestionsList(List.of(new OneChoiceQuestion()));
        secondTestPoll.setFewChoiceQuestionsList(List.of(new FewChoiceQuestion()));
        Poll controlPoll = adminService.updatePoll(1L,secondTestPoll);
        assertEquals(secondTestPoll,controlPoll);
        assertNotEquals(controlPoll,testPoll);
    }
}