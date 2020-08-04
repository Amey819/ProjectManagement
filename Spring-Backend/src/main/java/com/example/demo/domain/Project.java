package com.example.demo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;



// Project object class will have a name,id and description
// when it starts and ends, active or not

@Entity
public class Project {
	
	public Date getCreated_at() {
		return Created_at;
	}

	public void setCreated_at(Date created_at) {
		Created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Project Name is Required")
	private String projectName;
	@NotBlank(message = "Project Id is required")
	@Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
	@Column(updatable=false, unique=true)
	private String projectIdentifier;
	@NotBlank(message = "Please provide a description")
	private String description;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date start_date;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date end_date;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date Created_at;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date updated_at;
	
	
	public BackLog getBackLog() {
		return backLog;
	}

	public void setBackLog(BackLog backLog) {
		this.backLog = backLog;
	}
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project") //cascade means if backlog is deleted , all references are deleted
	@JsonIgnore
	private BackLog backLog;

	// constructor must be public  for the other clas to call this
	public Project()
	{	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectIdentifier() {
		return projectIdentifier;
	}

	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	// to update this with current date value
	// everytime it is created
	@PrePersist
	protected void onCreate()
	{
		this.Created_at = new Date();
	}
	
	// to update this with current date value
	// everytime it is updated
	@PreUpdate
	protected void onUpdate() {
		this.updated_at = new Date();
	}
}
