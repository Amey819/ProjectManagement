package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ProjectNotFoundException;
import com.example.demo.Repository.BackLogRepo;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.ProjectTaskRepo;
import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectTask;

@Service
public class ProjectTaskService {
	
	// put function of addProjectTask to BackLog
	@Autowired
	private BackLogRepo backLogRepo;
	
	@Autowired
	private ProjectTaskRepo projectTaskRepo;

	@Autowired
	private ProjectRepository projectRepo;
	
	// add Project Task
	// will take projectId, projectTask
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask)
	{
		
		// get the backlog from the project repo
		try
		{
		BackLog backLog = backLogRepo.findByProjectIdentifier(projectIdentifier);
		System.out.println(backLog);
		//set projectTask to backLog 
		// use the logic for project sequence
			// appned it to the project Identifier
		projectTask.setBackLog(backLog);
		Integer sequence = backLog.getPTSequence();
		sequence++;
		
		backLog.setPTSequence(sequence);
		projectTask.setProjectSequence(projectIdentifier+"-"+sequence);
		projectTask.setProjectIdentifier(projectIdentifier);
		
		// set for project priority
		if(projectTask.getPriority() == null)
		{
			projectTask.setPriority(3);
		}
		//set for projectstatus
		if(projectTask.getStatus() == null)
		{
			projectTask.setStatus("TO_DO");
		}
		
		return projectTaskRepo.save(projectTask);
		}
		catch(Exception e)
		{
			// instead of the null pointer exception 
			// it prints Project Id Not found
			throw new ProjectNotFoundException("Project Id "+projectIdentifier+" does not exist");
		}
		

	}
	
	public Iterable<ProjectTask> getProjectTasksById(String backlog_id)
	{
		return projectTaskRepo.findByProjectIdentifierOrderByPriority(backlog_id);
	}
	
	public ProjectTask getProjectTaskBySequence(String sequence) {
		
		return projectTaskRepo.findByProjectSequence(sequence);
	}
}
