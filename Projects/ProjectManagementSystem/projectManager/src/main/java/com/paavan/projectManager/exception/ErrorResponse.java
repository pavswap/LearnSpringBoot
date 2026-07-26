package com.paavan.projectManager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    ErrorResponse(String message, int status, LocalDateTime timestamp) {

        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    private String message;

    private int status;

    private LocalDateTime timestamp;

    private Map errors;
}