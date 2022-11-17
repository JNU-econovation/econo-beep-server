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
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[IllegalArgumentException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(AlreadyRentedException.class)
    public ResponseEntity<String> handleAlreadyRentedException(AlreadyRentedException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleAlreadyRentedException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity<String> handleNotFoundUserException(NotFoundUserException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleNotFoundUserException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(NotFoundRenteeException.class)
    public ResponseEntity<String> handleNotFoundRenteeException(NotFoundRenteeException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleNotFoundRenteeException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(NotFoundBookmarkException.class)
    public ResponseEntity<String> handleNotFoundBookmarkException(NotFoundBookmarkException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleNotFoundBookmarkException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(UnrentableException.class)
    public ResponseEntity<String> handleUnrentableException(UnrentableException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleUnrentableException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(UnreturnableException.class)
    public ResponseEntity<String> handleUnreturnableException(UnreturnableException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleUnreturnableException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(NotRenterException.class)
    public ResponseEntity<String> handleNotRenterException(NotRenterException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleNotRenterException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, String>> handleRequestBodyValidException(BindException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleRequestBodyValidException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleMissingServletRequestParameterException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(ImageIOException.class)
    public ResponseEntity<String> handleImageIOException(ImageIOException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleImageIOException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(WrongAccessTokenException.class)
    public ResponseEntity<String> handleWrongAccessTokenException(WrongAccessTokenException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleWrongAccessTokenException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }

    @ExceptionHandler(ExpiredAccessTokenException.class)
    public ResponseEntity<String> handleExpiredAccessTokenException(ExpiredAccessTokenException e, HttpServletRequest httpServletRequest) {
        String exceptionMessage = "[handleExpiredAccessTokenException] " + httpServletRequest.getRequestURL().toString() + " : " + e.getMessage();
        log.warn(exceptionMessage);


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionMessage);
    }
}
