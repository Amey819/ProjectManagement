package com.example.demo.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;



@Service
public class ErrorValidationService {
	
	
	public Map<String,String> checkerror(List<FieldError> list)
	{
		Map<String,String> map = new HashMap<>();
		for(FieldError errors: list)
		{
			map.put(errors.getField(), errors.getDefaultMessage());
		}
		return map;
	}
		

}
