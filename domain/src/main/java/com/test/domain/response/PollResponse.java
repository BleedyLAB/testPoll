package com.test.domain.response;

import com.test.entities.poll.Poll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PollResponse extends BaseResponse {
	private List<Poll> pollList;

}
