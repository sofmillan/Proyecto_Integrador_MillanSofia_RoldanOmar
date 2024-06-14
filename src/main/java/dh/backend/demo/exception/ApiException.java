package dh.backend.demo.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiException {
    private final String message;
    private final LocalDateTime dateTime;
    private final HttpStatus status;

    public ApiException(String message, LocalDateTime dateTime, HttpStatus status) {
        this.message = message;
        this.dateTime = dateTime;
        this.status = status;
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
