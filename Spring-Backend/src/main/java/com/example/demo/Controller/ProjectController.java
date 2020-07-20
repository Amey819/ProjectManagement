package com.example.demo.Controller;

import javax.validation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Services.ErrorValidationService;
import com.example.demo.Services.ProjectService;
import com.example.demo.domain.Project;



@RestController
@RequestMapping("/api/project")
public class ProjectController {

	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ErrorValidationService errorValidationService;
	// Responsentity gives more control on response json
	@PostMapping("/")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project,BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ResponseEntity<Map<String,String>>(errorValidationService.checkerror(result.getFieldErrors()),HttpStatus.BAD_REQUEST);
		}
 		
		
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
	}


}
