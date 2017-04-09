package com.aykan.web.exceptiýon;


public class InvalidLoginPassword extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidLoginPassword(String message){
		super(message);
	}
	
}
