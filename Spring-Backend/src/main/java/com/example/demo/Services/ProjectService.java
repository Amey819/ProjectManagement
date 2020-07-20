package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.ProjectRepository;
import com.example.demo.domain.Project;

// Services are built so that we donot put a lot of logic in the controller

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	// To save project on submit click
	public Project saveOrUpdateProject(Project project)
	{
		return projectRepository.save(project);
	}
	
	
	
}
