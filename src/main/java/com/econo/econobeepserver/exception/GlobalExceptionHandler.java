package com.econo.econobeepserver.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        String exceptionMessage = "[IllegalArgumentException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(AlreadyRentedException.class)
    public ResponseEntity<String> handleAlreadyRentedException(AlreadyRentedException e) {
        String exceptionMessage = "[handleAlreadyRentedException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<String> handleNotFoundUserException(NotFoundUserException e) {
        String exceptionMessage = "[handleNotFoundUserException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(NotFoundRenteeException.class)
    public ResponseEntity<String> handleNotFoundRenteeException(NotFoundRenteeException e) {
        String exceptionMessage = "[handleNotFoundRenteeException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(NotFoundBookmarkException.class)
    public ResponseEntity<String> handleNotFoundBookmarkException(NotFoundBookmarkException e) {
        String exceptionMessage = "[handleNotFoundBookmarkException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(UnrentableException.class)
    public ResponseEntity<String> handleUnrentableException(UnrentableException e) {
        String exceptionMessage = "[handleUnrentableException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(UnreturnableException.class)
    public ResponseEntity<String> handleUnreturnableException(UnreturnableException e) {
        String exceptionMessage = "[handleUnreturnableException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(NotRenterException.class)
    public ResponseEntity<String> handleNotRenterException(NotRenterException e) {
        String exceptionMessage = "[handleNotRenterException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, String>> handleRequestBodyValidException(BindException e) {
        String exceptionMessage = "[handleRequestBodyValidException] " + e.getMessage();
        log.warn(exceptionMessage);

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String exceptionMessage = "[handleMissingServletRequestParameterException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(ImageIOException.class)
    public ResponseEntity<String> handleImageIOException(ImageIOException e) {
        String exceptionMessage = "[handleImageIOException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(WrongAccessTokenException.class)
    public ResponseEntity<String> handleWrongAccessTokenException(WrongAccessTokenException e) {
        String exceptionMessage = "[handleWrongAccessTokenException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(ExpiredAccessTokenException.class)
    public ResponseEntity<String> handleExpiredAccessTokenException(ExpiredAccessTokenException e) {
        String exceptionMessage = "[handleExpiredAccessTokenException] " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }
}
