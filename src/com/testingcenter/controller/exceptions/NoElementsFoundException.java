package com.testingcenter.controller.exceptions;
/**
 * Class for exception when searching didnt found anything
 */
public class NoElementsFoundException extends RuntimeException {
    /**
     * Constructor for exception
     *
     * @param message exception message
     */
    public NoElementsFoundException(String message){
        super(message);
    }
}
