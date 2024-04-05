package com.cengizhanyavuz.wordvault.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class InsufficientWordsException extends RuntimeException {
    public InsufficientWordsException() {
        super("There is no word to learn today. Please come back tomorrow.");
    }
}
