package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptions.ProjectNotFoundException;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Services.ErrorValidationService;
import com.example.demo.Services.ProjectTaskService;
import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectTask;

@RestController
@RequestMapping("/backlog")
@CrossOrigin
public class ProjectTaskController {
	
	// create a addproject function
	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private ErrorValidationService errorValidationService;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addProjectTask(@Valid @RequestBody ProjectTask projectTask,@PathVariable String backlog_id,BindingResult result)
	{
		 // @ResponseBody means the returned String is the response, not a view name
	    // @RequestParam means it is a parameter from the GET or POST request
		if(result.hasErrors())
		{
			return new ResponseEntity<Map<String,String>>(errorValidationService.checkerror(result.getFieldErrors()),HttpStatus.BAD_REQUEST);
		}
		
		ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
		return new ResponseEntity<ProjectTask>(projectTask1,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{backlog_id}")
	public Iterable<ProjectTask> getProjectTask(String backlog_id)
	{	
		return projectTaskService.getProjectTasksById(backlog_id);
	}
	
	@GetMapping("/{backlog_id}/{sequence}")
	public ProjectTask findBySequence(@PathVariable String backlog_id, @PathVariable String sequence)
	{ 
		return projectTaskService.getProjectTaskBySequence(sequence);
	}

}
