package com.testingcenter.controller.exceptions;

/**
 * Class for exception when user input is incorrect
 */
public class IncorrectInputException extends RuntimeException {
    /**
     * Constructor for exception
     *
     * @param message exception message
     */
    public IncorrectInputException(String message) {
        super(message);
    }
}