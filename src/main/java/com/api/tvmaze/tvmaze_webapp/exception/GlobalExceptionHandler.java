package com.api.tvmaze.tvmaze_webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Metodo que maneja la excepcion IllegalArgumentException.
     * */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity .status(HttpStatus.BAD_REQUEST) .body(ex.getMessage());
    }

    /**
     * Metodo que maneja la excepcion DataNotFoundException.
     * */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleNotFound( DataNotFoundException ex) {
        return ResponseEntity .status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
