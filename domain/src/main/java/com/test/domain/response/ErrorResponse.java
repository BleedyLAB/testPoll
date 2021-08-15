package com.test.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = true)
public class ErrorResponse extends BaseResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String path;

	/**
	 * @param status  http com.test.response status code
	 * @param message Error message
	 * @param path    Path where exception was thrown
	 */
	public ErrorResponse(String message, HttpStatus status, String path) {
		super(message, status);
		this.timestamp = LocalDateTime.now();
		this.status = status.value();
		this.error = status.toString();
		this.path = path;
	}
}