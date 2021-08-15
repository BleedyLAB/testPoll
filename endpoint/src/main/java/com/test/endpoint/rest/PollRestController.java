package com.test.endpoint.rest;

import com.test.core.exception.PollIdNotFoundException;
import com.test.core.exception.UserIdNotFoundException;
import com.test.domain.dto.PollDTO;
import com.test.entities.poll.Poll;
import com.test.service.AdminService;
import com.test.service.mapper.PollMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.test.domain.response.BaseResponse;
import com.test.domain.response.ErrorResponse;
import com.test.domain.response.PollResponse;
import com.test.service.UserService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(PollRestController.BASE_URL)
@AllArgsConstructor
public class PollRestController {
    public final static String BASE_URL = "/api/polls";

    private final UserService userService;
    private final AdminService adminService;


    @GetMapping("/active")
    public ResponseEntity<List<Poll>> getActivePollList() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getListOfActivePoll());
    }

    @PostMapping("/start/{pollId}")
    public ResponseEntity<? extends BaseResponse> startPoll(@RequestBody Long userId,
                                                            @PathVariable Long pollId) {
        List<Poll> poll = new ArrayList<>();
        try {
            poll.add(userService.getPollForStart(userId, pollId));
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    "/api/polls/user/startPoll");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        PollResponse pollResponse = new PollResponse();
        pollResponse.setPollList(poll);
        return new ResponseEntity<>(pollResponse, HttpStatus.OK);
    }

    @PostMapping("/finish/{pollId}")
    public ResponseEntity<? extends BaseResponse> saveFinishedPoll(@RequestParam Long userId,
                                                                   @RequestBody PollDTO pollDTO) {
        try {
            userService.saveFinishedPoll(userId, new Poll());
        } catch (UserIdNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    "/api/polls/user/saveFinishedPoll");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<? extends BaseResponse> createPoll(@RequestHeader(value = "authorization") String authorization,
                                                             @RequestBody PollDTO pollDTO){
        if(!authorization.equals("adminAccess")){
            ErrorResponse errorResponse = new ErrorResponse(
                    "no access",
                    HttpStatus.BAD_REQUEST,
                    BASE_URL + "/createPoll");
            return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
        }
        adminService.createNewPoll(PollMapper.INSTANCE.pollDtoToPoll(pollDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<? extends BaseResponse> deletePoll(@RequestHeader(value = "authorization") String authorization,
                                                             @PathVariable Long pollId){
        if(!authorization.equals("adminAccess")){
            ErrorResponse errorResponse = new ErrorResponse(
                    "no access",
                    HttpStatus.BAD_REQUEST,
                    BASE_URL + "/deletePoll");
            return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
        }
        adminService.deletePoll(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{pollId}")
    public ResponseEntity<? extends BaseResponse> updatePoll(@RequestHeader(value = "authorization") String authorization,
                                                             @PathVariable Long pollId,
                                                             @RequestBody PollDTO pollDTO){
        if(!authorization.equals("adminAccess")){
            ErrorResponse errorResponse = new ErrorResponse(
                    "no access",
                    HttpStatus.BAD_REQUEST,
                    BASE_URL + "/deletePoll");
            return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
        }
        try{
            adminService.updatePoll(pollId,PollMapper.INSTANCE.pollDtoToPoll(pollDTO));
        } catch (PollIdNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    BASE_URL + "/deletePoll");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
