package com.project.BookMyShow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(GenException.class)
	public ResponseEntity<ErrorResponse> NotFoundException(GenException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
	}

}
