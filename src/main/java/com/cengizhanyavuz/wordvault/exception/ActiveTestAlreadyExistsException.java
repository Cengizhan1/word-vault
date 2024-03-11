package com.cengizhanyavuz.wordvault.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ActiveTestAlreadyExistsException extends RuntimeException {
    public ActiveTestAlreadyExistsException(String message) {
        super(message);
    }
}
