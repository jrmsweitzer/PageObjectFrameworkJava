package org.catalystitservices.PageObjectFramework.Framework.Exceptions;

public class InvalidSelectOptionException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public InvalidSelectOptionException()
    {
    	super();
    }

    public InvalidSelectOptionException(String message)
    {
    	super(message);
    }
}
