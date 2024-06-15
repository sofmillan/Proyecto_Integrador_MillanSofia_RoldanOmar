package dh.backend.demo.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiException {
    private final String message;
    private final LocalDateTime dateTime;
    private final HttpStatus status;
    private final Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public ApiException(String message, LocalDateTime dateTime, HttpStatus status, Integer statusCode) {
        this.message = message;
        this.dateTime = dateTime;
        this.status = status;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
