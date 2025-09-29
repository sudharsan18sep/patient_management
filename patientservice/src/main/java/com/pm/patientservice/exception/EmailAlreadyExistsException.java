package com.pm.patientservice.exception;

//we use custom exception or else it will consider that error as normal runtime exception
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
