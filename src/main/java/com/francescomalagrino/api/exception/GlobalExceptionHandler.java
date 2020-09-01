package com.francescomalagrino.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.naming.directory.InvalidAttributesException;
import java.security.InvalidParameterException;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDetails> handleBadRequest(InvalidParameterException exception, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDetails()
                ,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAttributesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionDetails> handleBadRequest(InvalidAttributesException exception, WebRequest request) {
        return error(HttpStatus.BAD_REQUEST,exception, request);
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionDetails> handleGlobalException(Exception exception, WebRequest request) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR,exception, request);
    }
    private ResponseEntity<ExceptionDetails> error(HttpStatus status, Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ExceptionDetails()
                ,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}