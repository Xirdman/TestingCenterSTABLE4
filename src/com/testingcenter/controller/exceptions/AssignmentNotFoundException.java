package com.testingcenter.controller.exceptions;

/**
 * Class for exception when assignment not found
 */
public class AssignmentNotFoundException extends RuntimeException {
    /**
     * Constructor for exception
     *
     * @param message exception message
     */
    public AssignmentNotFoundException(String message) {
        super(message);
    }
}
