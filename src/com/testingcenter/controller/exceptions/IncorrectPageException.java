package com.testingcenter.controller.exceptions;

/**
 * Class for exception when we try to reach non-existent page of collection
 *
 * @author Matveev Alexander
 */
public class IncorrectPageException extends RuntimeException {
    /**
     * Constructor for exception
     *
     * @param message exception message
     */
    public IncorrectPageException(String message) {
        super(message);
    }
}
