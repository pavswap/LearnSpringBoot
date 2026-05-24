package com.Valid.ValidExcep.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private String message;
    private int httpStatus;
    private LocalDateTime time;

    ErrorResponse(String message, int httpStatus, LocalDateTime time) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
