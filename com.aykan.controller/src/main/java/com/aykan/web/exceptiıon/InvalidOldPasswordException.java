package com.aykan.web.excepti�on;

public class InvalidOldPasswordException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidOldPasswordException() {
	}
	
	public InvalidOldPasswordException(String message) {
		super(message);
	}

}
