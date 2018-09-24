package com.stackroute.tldm.exception;

public class MessageNotFoundException extends Exception {
	
	  public static final long serialVersionUID = 1L;

	  // this is inititaed to throw an exception when a specific message is not found
	  
	    public MessageNotFoundException(String message) {
	        super(message);
	    }

}
