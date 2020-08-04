package com.example.demo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProjectTask {

	// id
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	public ProjectTask()
	{
		
	}
	// projectsequence // to identify this task
	@Column(updatable = false)
	private String projectSequence;
	// summary // about the task
	@NotBlank(message="Please add a Summary")
	private String summary;
	private String sequence;
	// acceptance criteria // when is it completed
	private String acceptance_criteria;
	// status  // active or not
	private String status;
	// priority // high priority stay first
	private Integer priority;
	// due_date  // when should this be compeleted
	private Date due_date;
	// Many to one with backlog 
	// projectIdentifier -> to identify the project 
	@Column(updatable = false)
	private String projectIdentifier;
	//created_at
	private Date created_at;
	//updated_at
	private Date updated_at;
	
	// Many To One with backLog
	  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	  @JoinColumn(name="backlog_id", updatable = false, nullable = false)
	  @JsonIgnore
	  private BackLog backLog;
	// prepersist
	@PrePersist
	protected void onCreate() {
		this.created_at = new Date();
		
	}
	public long getId() {
		return id;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public BackLog getBackLog() {
		return backLog;
	}
	public void setBackLog(BackLog backLog) {
		this.backLog = backLog;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public void setId(long id) {
		this.id = id;
	}


	public String getProjectSequence() {
		return projectSequence;
	}
	public void setProjectSequence(String projectSequence) {
		this.projectSequence = projectSequence;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getAcceptance_criteria() {
		return acceptance_criteria;
	}
	public void setAcceptance_criteria(String acceptance_criteria) {
		this.acceptance_criteria = acceptance_criteria;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	

	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	@Override
	public String toString() {
		return "ProjectTask [id=" + id + ", projectSequence=" + projectSequence + ", summary=" + summary + ", sequence="
				+ sequence + ", acceptance_criteria=" + acceptance_criteria + ", status=" + status + ", priority="
				+ priority + ", due_date=" + due_date + ", projectIdentifier=" + projectIdentifier + ", created_at="
				+ created_at + ", updated_at=" + updated_at + "]";
	}
	
	// preupdate
	@PreUpdate
	protected void onUpdate()
	{
		this.updated_at = new Date();
	}
	
}
