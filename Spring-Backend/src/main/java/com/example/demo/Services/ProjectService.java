package com.example.demo.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exceptions.ProjectIdException;
import com.example.demo.Repository.BackLogRepo;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;

// Services are built so that we donot put a lot of logic in the controller

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired 
	private BackLogRepo backLogRepo;
	// To save project on submit click
	public Project saveOrUpdateProject(Project project)
	{
		try
		{
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			// BackLog will be created only when project is created
			if(project.getId() == null)
			{
				BackLog backLog = new BackLog();  // means if project is created not updated backlog instance created
				project.setBackLog(backLog);
				backLog.setProject(project);
				backLog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			if(project.getId() != null){
				// set backlog to project
				project.setBackLog(backLogRepo.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			
			 
			return projectRepository.save(project);
		}
		catch(Exception e)
		{
			throw new ProjectIdException("Project Id"+project.getProjectIdentifier().toUpperCase()+" Already exists");
		}
	}
	
	public Project findProjectById(String projectIdentifier)
	{
		return projectRepository.findByProjectIdentifier(projectIdentifier);
	}
	
	public Iterable<Project> findAllProjects()
	{
		return projectRepository.findAll();
	}
	
	public void deleteProject(String projectIdentifier)
	{
		Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
		if(project == null)
		{
			throw new ProjectIdException("Cannot delete this project " +projectIdentifier+ "does nto exist");
		}
		projectRepository.delete(project);
	}
	

}
