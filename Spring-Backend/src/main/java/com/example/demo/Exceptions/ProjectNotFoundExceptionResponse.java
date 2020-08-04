package com.example.demo.Exceptions;

public class ProjectNotFoundExceptionResponse {

	
	// To throw this exception wherenver project is not found
	private String projectNotFound;

	public ProjectNotFoundExceptionResponse(String projectNotFound) {
		super();
		this.projectNotFound = projectNotFound;
	}

	public String getProjectNotFound() {
		return projectNotFound;
	}

	public void setProjectNotFound(String projectNotFound) {
		this.projectNotFound = projectNotFound;
	}
	
	
}
