package com.test.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

	private String message;

	private HttpStatus httpStatus;

	/**
	 * @param message Сообщение в случае удачного запроса
	 */
	public BaseResponse(String message) {
		this.message = message;
		httpStatus = HttpStatus.OK;
	}
}
