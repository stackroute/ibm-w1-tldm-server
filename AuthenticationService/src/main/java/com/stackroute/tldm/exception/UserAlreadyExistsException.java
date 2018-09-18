package com.stackroute.tldm.exception;

public class UserAlreadyExistsException extends Exception {

	public static final long serialVersionID = 1;

	public UserAlreadyExistsException(String message) {
		super(message);
	}

}
