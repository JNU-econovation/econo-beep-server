package com.econo.econobeepserver.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundRenteeException.class)
    public ResponseEntity<String> handleNotFoundRenteeException(NotFoundRenteeException e) {
        log.error("handleNotFoundRenteeException", e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(AlreadyRentedException.class)
    public ResponseEntity<String> handleAlreadyRentedException(AlreadyRentedException e) {
        log.error("handleAlreadyRentedException", e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(UnrentableException.class)
    public ResponseEntity<String> handleUnrentableException(UnrentableException e) {
        log.error("handleUnrentableException", e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(NotRenterException.class)
    public ResponseEntity<String> handleNotRenterException(NotRenterException e) {
        log.error("handleNotRenterException", e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleRequestBodyValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));

        return ResponseEntity
                .badRequest()
                .body(errors);
    }
}
