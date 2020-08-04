package com.example.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// THIS SERVICE SHOULD THROW THIS response status
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {

	public ProjectIdException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}
