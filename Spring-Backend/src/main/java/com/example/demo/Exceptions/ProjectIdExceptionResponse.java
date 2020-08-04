package com.example.demo.Exceptions;

public class ProjectIdExceptionResponse {
	
	// Id same project id exists again
	// then check with database first
	// then send response errror if already exists
	
	private String projectIdentifier;
	
	public ProjectIdExceptionResponse(String projectIdentifier)
	{
		this.projectIdentifier = projectIdentifier;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}



	

	

}
