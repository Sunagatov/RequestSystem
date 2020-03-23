package com.zufar.requestsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends RuntimeException {

    public RequestNotFoundException() {
        super();
    }

    public RequestNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestNotFoundException(String message) {
        super(message);
    }

    public RequestNotFoundException(Throwable cause) {
        super(cause);
    }
}