package com.econo.econobeepserver.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}