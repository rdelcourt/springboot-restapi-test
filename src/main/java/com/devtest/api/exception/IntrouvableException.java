package com.devtest.api.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Exception class to manage the HTTP response when an object is not found by the REST API
 * @author rdelcourt
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class IntrouvableException extends RuntimeException {

    public IntrouvableException(String s) {
        super(s);
    }

}
