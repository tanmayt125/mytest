package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaytmServerDownException.class)
    public ResponseEntity<?>handlePaytmServerDownException(PaytmServerDownException exception){
        return new ResponseEntity(exception.getMessage(), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

}
