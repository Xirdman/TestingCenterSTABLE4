package com.testingcenter.controller.exceptions;

/**
 * Class for exception when group not found
 */
public class GroupNotFoundException extends RuntimeException {
    /**
     * Constructor for exception
     *
     * @param message exception message
     */
    public GroupNotFoundException(String message) {
        super(message);
    }
}
