package com.api.tvmaze.tvmaze_webapp.exception;

public class DataNotFoundException extends RuntimeException {

    /**
     * Constructor de la clase con mensaje personalizado.
     * */
    public DataNotFoundException(String message) {
        super(message);
    }
}
