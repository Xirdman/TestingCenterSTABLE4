package com.testingcenter.controller.exceptions;

/**
 * Class for exception when group not found
 */
public class NoTestsForTeacherException extends RuntimeException {
    /**
     * Constructor for exception
     *
     * @param message exception message
     */
    public NoTestsForTeacherException(String message) {
        super(message);
    }
}