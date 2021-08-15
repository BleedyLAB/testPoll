package com.test.endpoint.rest;

import com.test.core.exception.UserIdNotFoundException;
import com.test.domain.response.BaseResponse;
import com.test.domain.response.ErrorResponse;
import com.test.domain.response.PollResponse;
import com.test.entities.poll.Poll;
import com.test.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/results/{userId}")
    public ResponseEntity<? extends BaseResponse> getUserFinishedPoll(@PathVariable Long userId) {
        List<Poll> polls;
        try {
            polls = userService.getFinishedPollList(userId);
        } catch (UserIdNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    "User not found",
                    HttpStatus.BAD_REQUEST,
                    "/api/users/getFinishedPoll");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        PollResponse pollResponse = new PollResponse(polls);
        return new ResponseEntity<>(pollResponse, HttpStatus.OK);
    }


}
