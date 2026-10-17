package com.Valid.ValidExcep.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalRollException.class)
    public ResponseEntity<ErrorResponse> handleIllegalRoll(IllegalRollException e) {

        var response = new ErrorResponse(e.getMessage(), 400, LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
