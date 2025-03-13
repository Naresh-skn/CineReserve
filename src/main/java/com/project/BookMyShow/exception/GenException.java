package com.project.BookMyShow.exception;

import java.io.Serial;

public class GenException extends RuntimeException{
	
	 @Serial
	 private static final long serialVersionUID = 1L;

	    public GenException() {
	    }

	    public GenException(String message) {
	        super(message);
	    }

}
