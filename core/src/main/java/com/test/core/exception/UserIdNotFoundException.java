package com.test.core.exception;


public class UserIdNotFoundException extends Exception {

	/**
	 * @param message Сообщение в случае шибки
	 */
	public UserIdNotFoundException(String message) {
		super(message);
	}

}
