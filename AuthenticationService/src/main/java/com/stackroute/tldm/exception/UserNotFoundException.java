package com.stackroute.tldm.exception;

public class UserNotFoundException extends Exception {

	public static final long serialVersionID = 1;

	public UserNotFoundException(String message) {
		super(message);
	}

}
