package dh.backend.demo.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> recursoNoEncontrado(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
    }
}
