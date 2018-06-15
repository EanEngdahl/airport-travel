package org.airlinesystem.exceptions;

public class IllegalGraphAdditionException extends Exception {

	private static final long serialVersionUID = 6234124611862832035L;

	public IllegalGraphAdditionException(String message) {
    	super(message);
    }

    public IllegalGraphAdditionException (String message, Throwable throwable) {
    	super(message, throwable);
    }

}
