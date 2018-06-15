package org.airlinesystem.exceptions;

public class IllegalGraphAdditionException extends Exception {

    public IllegalGraphAdditionException(String message) {
    	super(message);
    }

    public IllegalGraphAdditionException (String message, Throwable throwable) {
    	super(message, throwable);
    }

}
