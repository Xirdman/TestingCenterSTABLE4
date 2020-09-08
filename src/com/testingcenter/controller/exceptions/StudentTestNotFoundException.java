package com.testingcenter.controller.exceptions;

/**
 * Class for exception when group not found
 */
public class StudentTestNotFoundException extends RuntimeException {
    /**
     * Constructor for exception
     *
     * @param message exception message
     */
    public StudentTestNotFoundException(String message) {
        super(message);
    }
}
