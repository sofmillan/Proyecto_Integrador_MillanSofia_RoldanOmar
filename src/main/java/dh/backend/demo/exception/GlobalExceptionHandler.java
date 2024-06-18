package dh.backend.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiException> recursoNoEncontrado(ResourceNotFoundException e){
        ApiException apiException = new ApiException(e.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND, 404);
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(apiException);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException e){
        ApiException apiException = new ApiException(e.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST, 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
    }
}
