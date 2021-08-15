package com.test.service;

import com.test.core.exception.PollIdNotFoundException;
import com.test.core.exception.UserIdNotFoundException;
import com.test.domain.repository.PollRepository;
import com.test.domain.repository.UserRepository;
import com.test.entities.poll.Poll;
import com.test.entities.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PollRepository pollRepository;
    @Spy
    @InjectMocks
    UserServiceImpl userService;
    private List<Poll> testPoll;
    private User testUser;
    @BeforeEach
    void setup() {
        this.testPoll = List.of(new Poll(),new Poll(),new Poll());
        testPoll.get(0).setArchive(true);
        User testUser = new User();
        List<Poll> list = new ArrayList<>(List.of(new Poll(), new Poll()));
        testUser.setFinishedPoll(list);
        this.testUser = testUser;
    }
    @Test
    void getListOfActivePoll() {
        Mockito.when(pollRepository.findAll()).thenReturn(testPoll);
        List<Poll> activePoll = userService.getListOfActivePoll();
        assertEquals(2,activePoll.size());
        assertNotEquals(3,activePoll.size());
    }

    @Test
    void getPollForStart() throws PollIdNotFoundException {
        Mockito.when(pollRepository.findById(any())).thenReturn(Optional.of(new Poll()));
        userService.getPollForStart(1L,1L);
        InOrder inOrder = Mockito.inOrder(userService,pollRepository,userRepository);
        inOrder.verify(userRepository).findById(any());
        inOrder.verify(userRepository).save(any());
        inOrder.verify(pollRepository).findById(any());
    }

    @Test
    void getFinishedPollList() throws UserIdNotFoundException {

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(testUser));
        List<Poll> userPoll = userService.getFinishedPollList(1L);
        assertEquals(2,userPoll.size());
        assertNull(userPoll.get(0).getDescription());
    }

    @Test
    void saveFinishedPoll() throws UserIdNotFoundException {
        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(testUser));
        User user = userService.saveFinishedPoll(1L, new Poll());
        InOrder inOrder = Mockito.inOrder(userService,pollRepository,userRepository);
        inOrder.verify(userRepository).findById(any());
        inOrder.verify(userRepository).save(any());
        assertEquals(3,user.getFinishedPoll().size());

    }
}