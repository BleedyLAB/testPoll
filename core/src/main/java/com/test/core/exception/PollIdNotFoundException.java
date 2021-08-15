package com.test.core.exception;

public class PollIdNotFoundException extends Exception{
    /**
     * @param message Сообщение в случае шибки
     */
    public PollIdNotFoundException(String message) {
        super(message);
    }

}
