package com.cengizhanyavuz.wordvault.exception;

import com.cengizhanyavuz.wordvault.model.Word;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class InsufficientWordsException extends RuntimeException {
    public InsufficientWordsException() {
        super("There is no word to learn today. Please come back tomorrow.");
    }
}
