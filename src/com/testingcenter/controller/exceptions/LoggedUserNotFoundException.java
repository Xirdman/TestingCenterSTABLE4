package com.testingcenter.controller.exceptions;

/**
 * Class for exception when group not found
 */
public class LoggedUserNotFoundException extends RuntimeException {
    /**
     * Constructor for exception
     *
     * @param message exception message
     */
    public LoggedUserNotFoundException(String message) {
        super(message);
    }
}

