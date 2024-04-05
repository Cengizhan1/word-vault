package com.cengizhanyavuz.wordvault.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class WordExistsException extends RuntimeException {
    public WordExistsException(String s) {
        super(s);
    }
}
