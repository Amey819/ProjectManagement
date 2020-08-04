package com.example.demo.domain;


import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;



// Backlog is the project that has not been completed
@Entity
public class BackLog {

	public List<ProjectTask> getProjectTask() {
		return projectTask;
	}
	public void setProjectTask(List<ProjectTask> projectTask) {
		this.projectTask = projectTask;
	}
	// id
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto incrment
	private long id;
	//PTSequence
	private Integer PTSequence = 0;
	// projectIdenriier
	@Column(updatable = false)
	private String projectIdentifier;
	// one to one with project
	public BackLog() {
		
	}
	// one to many projectTasks -> backlog can have many project tasks
	@OneToOne(fetch = FetchType.EAGER) // Eager loads all the relationships while lazy does not
	@JoinColumn(name = "project_id", nullable = false)
	@JsonIgnore // it will set it to nulll from the serialization process and the output would have value in it
	private Project project;
	
	// Create a OneToMany projectTasks
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "backLog") // if backlog is deleted, all the tasks are deleted with it
	private List<ProjectTask> projectTask = new ArrayList<ProjectTask>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getPTSequence() {
		return PTSequence;
	}
	public void setPTSequence(Integer pTSequence) {
		PTSequence = pTSequence;
	}
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String string) {
		this.projectIdentifier = string;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
}
