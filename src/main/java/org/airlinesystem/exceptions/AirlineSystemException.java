package org.airlinesystem.exceptions;

public class AirlineSystemException extends Exception {

	private static final long serialVersionUID = -7940366909339982987L;
	
	public AirlineSystemException(String message) {
    	super(message);
    }

    public AirlineSystemException(String message, Throwable throwable) {
    	super(message, throwable);
    }

    public AirlineSystemException(Throwable throwable) {
    	super(throwable);
    }
}
